/*
 * Tests af detaljesiden (detailsActivity.html + CSS + JS).
 */
const { loadPage, missingCssClasses, mockFetch } = require('./helpers');
const { gocart, minigolf } = require('./testData');
const { getActivityDetail } = require('../../main/resources/static/js/detailsActivityJavaScript.js');

/*
 * Sætter URL'en i den falske browser, fx ?id=Gocart,
 * så window.location.search virker i JS-filen.
 */
function goToUrl(query) {
    window.history.pushState({}, '', `/detailsActivity.html${query}`);
}

/* Finder værdien i en fact-row, fx factValue('Varighed') -> '30 min' */
function factValue(label) {
    const line = Array.from(document.querySelectorAll('.fact-row'))
        .find(l => l.querySelector('span').textContent === label);
    return line.querySelector('strong').textContent;
}

describe('Detaljeside', () => {

    beforeEach(() => {
        loadPage('html/detailsActivity.html', 'css/detailsActivityCSS.css');
    });

    test('henter aktiviteten fra id i URL\'en og viser fakta', async () => {
        goToUrl('?id=Gocart');
        const fetchMock = mockFetch(gocart);

        await getActivityDetail();

        expect(fetchMock).toHaveBeenCalledWith('/adventure/activity/Gocart');
        expect(document.querySelector('h1').textContent).toBe('Gocart');
        expect(factValue('Varighed')).toBe('30 min');
        expect(factValue('Aldersgrænse')).toBe('14 år');
        expect(factValue('Højdegrænse')).toBe('150 cm');
    });

    test('udstyr bliver til ét listepunkt per ting', async () => {
        goToUrl('?id=Gocart');
        mockFetch(gocart);

        await getActivityDetail();

        const items = Array.from(document.querySelectorAll('.fact-box li')).map(li => li.textContent);
        expect(items).toEqual(['Hjelm', 'Balaclava', 'Kørerdragt']);
    });

    test('"Ingen" udstyr giver ét listepunkt', async () => {
        goToUrl('?id=Minigolf');
        mockFetch(minigolf);

        await getActivityDetail();

        expect(document.querySelectorAll('.fact-box li').length).toBe(1);
    });

    test('viser en fejlbesked ved 404', async () => {
        goToUrl('?id=Bowling');
        mockFetch(null, 404);

        await getActivityDetail();

        expect(document.querySelector('#activity-error')).not.toBeNull();
        expect(document.querySelector('.fact-box')).toBeNull();
    });

    test('CSS: alle klasser på siden har styling', async () => {
        goToUrl('?id=Gocart');
        mockFetch(gocart);
        await getActivityDetail();

        expect(missingCssClasses('css/detailsActivityCSS.css')).toEqual([]);
    });

    test('CSS: beskrivelsen bevarer linjeskift fra enum\'en', async () => {
        /*
         * Beskrivelserne har \n\n. Uden white-space: pre-line
         * ville browseren vise det som ét langt afsnit.
         */
        goToUrl('?id=Gocart');
        mockFetch(gocart);
        await getActivityDetail();

        expect(getComputedStyle(document.querySelector('.detail-text p')).whiteSpace).toBe('pre-line');
    });
});