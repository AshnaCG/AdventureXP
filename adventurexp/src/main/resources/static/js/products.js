// Viser varerne fra backenden som et menukort i hero-sektionen.
// Siden er kun informativ: ingen kurv, ingen køb.

const list = document.getElementById("product-list");
const statusBox = document.getElementById("product-status");

async function loadProducts() {
    try {
        const response = await fetch("/adventure/products");
        if (!response.ok) {
            throw new Error("Serveren svarede med status " + response.status);
        }
        const products = await response.json();
        showProducts(products);
    } catch (error) {
        console.error(error);
        showStatus("Vi kunne ikke hente varerne lige nu. Prøv igen senere.", true);
    }
}

function showProducts(products) {
    list.replaceChildren();

    if (products.length === 0) {
        showStatus("Der er ingen varer på listen endnu.", false);
        return;
    }
    statusBox.hidden = true;

    // Grupper varerne efter kategori: { "Sodavand": [...], "Slik": [...] }
    const byCategory = new Map();
    for (const product of products) {
        const categoryName = product.category ? product.category.name : "Andet";
        if (!byCategory.has(categoryName)) {
            byCategory.set(categoryName, []);
        }
        byCategory.get(categoryName).push(product);
    }

    const categoryNames = [...byCategory.keys()].sort((a, b) => a.localeCompare(b, "da"));
    for (const categoryName of categoryNames) {
        const items = byCategory.get(categoryName)
            .sort((a, b) => a.name.localeCompare(b.name, "da"));
        list.appendChild(createCategory(categoryName, items));
    }
}

function createCategory(categoryName, items) {
    const section = document.createElement("section");
    section.className = "menu-category";

    const heading = document.createElement("h3");
    heading.textContent = categoryName;
    section.appendChild(heading);

    const ul = document.createElement("ul");
    ul.className = "menu-items";
    for (const product of items) {
        ul.appendChild(createMenuItem(product));
    }
    section.appendChild(ul);

    return section;
}

function createMenuItem(product) {
    const li = document.createElement("li");
    li.className = "menu-item";

    const row = document.createElement("div");
    row.className = "menu-row";

    const name = document.createElement("span");
    name.className = "menu-name";
    name.textContent = product.name;

    const dots = document.createElement("span");
    dots.className = "menu-dots";

    const price = document.createElement("span");
    price.className = "menu-price";
    price.textContent = product.price + " kr.";

    row.append(name, dots, price);
    li.appendChild(row);

    if (product.description) {
        const description = document.createElement("p");
        description.className = "menu-description";
        description.textContent = product.description;
        li.appendChild(description);
    }

    return li;
}

function showStatus(message, isError) {
    statusBox.textContent = message;
    statusBox.classList.toggle("product-status-error", isError);
    statusBox.hidden = false;
}

loadProducts();
