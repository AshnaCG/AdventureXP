/*
 * Tests af aktivitetsoversigten (activities.html + CSS + JS).
 *
 * describe grupperer tests, test er én test,
 * expect(x).toBe(y) svarer til assertEquals i JUnit.
 */
const { staticFileExists, loadPage, missingCssClasses, mockFetch } = require('./helpers');
const { activities } = require('./testData');
const { getActivities } = require('../../main/resources/static/activitiesJavaScript.js');

describe('Aktivitetsoversigt', () => {

    beforeEach(() => {
        loadPage('activities.html', 'activitiesCSS.css');
    });

    test('henter aktiviteter og laver ét kort per aktivitet', async () => {
        // await venter på at den async funktion er færdig, før vi tjekker
        const fetchMock = mockFetch(activities);

        await getActivities();

        expect(fetchMock).toHaveBeenCalledWith('/adventure/activity');
        expect(document.querySelectorAll('.aktivitet-kort').length).toBe(2);
    });

    test('kortet viser de rigtige data og linker til detaljesiden', async () => {
        mockFetch(activities);

        await getActivities();

        const card = document.querySelector('.aktivitet-kort');
        expect(card.getAttribute('href')).toBe('detailsActivity.html?id=Gocart');
        expect(card.querySelector('h3').textContent).toBe('Gocart');
        expect(card.querySelector('.badge').textContent).toBe('14+ år');
        expect(card.querySelector('p').textContent).toContain('30 min');
        expect(staticFileExists(card.querySelector('img').getAttribute('src'))).toBe(true);
    });

    test('viser en fejlbesked hvis serveren fejler', async () => {
        mockFetch(null, 500);

        await getActivities();

        expect(document.querySelector('.fejl')).not.toBeNull();
        expect(document.querySelectorAll('.aktivitet-kort').length).toBe(0);
    });

    test('CSS: alle klasser på siden har styling', async () => {
        mockFetch(activities);
        await getActivities();

        // toEqual([]) = der må ikke mangle nogen
        expect(missingCssClasses('activitiesCSS.css')).toEqual([]);
    });

    test('CSS: kortene står i et grid med 2 kolonner', () => {
        const style = getComputedStyle(document.getElementById('aktivitet-liste'));

        expect(style.display).toBe('grid');
        expect(style.gridTemplateColumns).toBe('repeat(2, 1fr)');
    });
});
