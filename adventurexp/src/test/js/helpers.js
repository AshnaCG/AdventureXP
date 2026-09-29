/*
 * Fælles hjælpefunktioner til frontend-testene.
 * Jest kører med "jsdom", en falsk browser, så vi har document og getComputedStyle.
 */
const fs = require('fs');
const path = require('path');

const STATIC_DIR = path.join(__dirname, '../../main/resources/static');

function readStaticFile(fileName) {
    return fs.readFileSync(path.join(STATIC_DIR, fileName), 'utf8');
}

function staticFileExists(fileName) {
    return fs.existsSync(path.join(STATIC_DIR, fileName));
}

/*
 * Lægger HTML-filens <body> og CSS-filen ind i test-dokumentet,
 * så JS'en finder sine elementer, og getComputedStyle virker.
 */
function loadPage(htmlFile, cssFile) {
    const parsed = new DOMParser().parseFromString(readStaticFile(htmlFile), 'text/html');
    document.body.innerHTML = parsed.body.innerHTML;
    document.head.innerHTML = `<style>${readStaticFile(cssFile)}</style>`;
}

/*
 * Tjekker at alle klasser på siden står i CSS-filen.
 * Fanger stavefejl som "aktivitets-kort" i JS'en.
 * Returnerer de klasser der mangler (tomt array = alt er godt).
 */
function missingCssClasses(cssFile) {
    const css = readStaticFile(cssFile);
    const missing = [];
    document.querySelectorAll('[class]').forEach(el => {
        el.classList.forEach(c => {
            if (!css.includes('.' + c)) missing.push(c);
        });
    });
    return missing;
}

/*
 * Erstatter fetch med en falsk version.
 * jest.fn() husker hvordan den blev kaldt, og mockResolvedValue
 * gør at den returnerer et Promise med vores data, som rigtig fetch.
 */
function mockFetch(data, status = 200) {
    global.fetch = jest.fn().mockResolvedValue({
        ok: status >= 200 && status < 300,
        json: async () => data
    });
    return global.fetch;
}

module.exports = { staticFileExists, loadPage, missingCssClasses, mockFetch };