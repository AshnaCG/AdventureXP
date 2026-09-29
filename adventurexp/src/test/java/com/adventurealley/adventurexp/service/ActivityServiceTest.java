package com.adventurealley.adventurexp.service;

import com.adventurealley.adventurexp.activity.Activity;
import com.adventurealley.adventurexp.activity.ActivityRepository;
import com.adventurealley.adventurexp.activity.ActivityService;
import com.adventurealley.adventurexp.equipment.Equipment;
import com.adventurealley.adventurexp.equipment.State;
import com.adventurealley.adventurexp.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {

    @Mock
    private ActivityRepository activityRepository;

    @InjectMocks
    private ActivityService activityService;

    private Activity gokart() {
        Activity a = new Activity("Gokart", "Kør om kap", "/image/Gokart.jpg", 30, 14, 150);
        a.setId(1L);
        return a;
    }

    @Test
    void createHappyFlow() {
        // precondition
        Activity nyAktivitet = new Activity("Klatring", "Klatrevæg", "", 45, 8, 120);
        when(activityRepository.findByNameIgnoreCase("Klatring")).thenReturn(Optional.empty());
        when(activityRepository.save(any(Activity.class))).thenAnswer(inv -> inv.getArgument(0));

        // execution
        Activity result = activityService.create(nyAktivitet);

        // post condition
        assertEquals("Klatring", result.getName());
        assertEquals(8, result.getMinAge());
        verify(activityRepository).save(nyAktivitet);
    }

    @Test
    void createWithNegativeAgeExceptionFlow() {
        Activity nyAktivitet = new Activity("Klatring", "", "", 45, -1, 0);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> activityService.create(nyAktivitet));

        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
        verify(activityRepository, never()).save(any());
    }

    @Test
    void createWithDuplicateNameExceptionFlow() {
        when(activityRepository.findByNameIgnoreCase("gokart")).thenReturn(Optional.of(gokart()));
        Activity nyAktivitet = new Activity("gokart", "", "", 30, 0, 0);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> activityService.create(nyAktivitet));

        assertEquals(HttpStatus.CONFLICT, ex.getStatusCode());
    }

    @Test
    void updateAgeLimitHappyFlow() {
        // precondition
        Activity existing = gokart();
        when(activityRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(activityRepository.findByNameIgnoreCase("Gokart")).thenReturn(Optional.of(existing));
        when(activityRepository.save(any(Activity.class))).thenAnswer(inv -> inv.getArgument(0));
        Activity changes = new Activity("Gokart", "Kør om kap", "/image/Gokart.jpg", 30, 16, 155);

        // execution
        Activity result = activityService.update(1L, changes);

        // post condition
        assertEquals(1L, result.getId());
        assertEquals(16, result.getMinAge());
        assertEquals(155, result.getMinHeight());
    }

    @Test
    void updateUnknownIdExceptionFlow() {
        when(activityRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> activityService.update(99L, gokart()));
    }

    @Test
    void deleteKeepsEquipmentButDetachesIt() {
        // precondition
        Activity existing = gokart();
        Equipment hjelm = new Equipment("Hjelm", true, State.OK);
        existing.addEquipment(hjelm);
        when(activityRepository.findById(1L)).thenReturn(Optional.of(existing));

        // execution
        activityService.delete(1L);

        // post condition
        assertNull(hjelm.getActivity());
        verify(activityRepository).delete(existing);
    }
}
