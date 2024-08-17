const importDocInput = document.getElementById("import-document-input");
const importDocButton = document.getElementById("import-document-button");

const importFilterInput = document.getElementById("import-filter-input");
const importFilterButton = document.getElementById("import-filter-button");

const elementCoreContainer = document.getElementById("core-container");
const elementList = document.getElementById("list-printers");
const elementTitleList = document.getElementById("title-list-printers");

const printerInfoModal = document.getElementById("printerInfoModal");
const titleModal = document.getElementById("printer-info-modal-label");
const tableOptions = document.getElementById("printer-option-table");

printerInfoModal.addEventListener('hidden.bs.modal', function () {
    tableOptions.innerHTML = "";
})

importDocButton.addEventListener("click", function () {
    importDocInput.click();
});

importDocInput.addEventListener("change", function () {
    const url = "/api/v1/printers/importDocument";
    let formData = new FormData();
    formData.append("file", importDocInput.files[0]);

    fetch(url, {
        method: "POST",
        body: formData
    }).then(response => checkResponse(response))
        .then(json => importPrinters(json, "То что заимпортилось в бд:"))
})

importFilterButton.addEventListener("click", function () {
    importFilterInput.click();
})

importFilterInput.addEventListener("change", function () {
    const url = "/api/v1/printers/all";
    let formData = new FormData();
    formData.append("filter", importFilterInput.files[0]);

    fetch(url, {
        method: "POST",
        body: formData
    }).then(response => checkResponse(response))
        .then(json => {
            importPrinters(json, "Отфильтрованное, по запросу:");
            addCancelButton();
        });
});


fetch("api/v1/printers/all", {
    method: "GET"
}).then(response => checkResponse(response))
    .then(json => importPrinters(json, "Принтеры из бд:"));

const checkResponse = (response) => {
    if (response.ok) {
        return response.json();
    }
    const errorContainer = document.createElement("div");
    errorContainer.setAttribute("class", "alert alert-danger");
    errorContainer.innerText = "Произошла ябучая ошибка: \n";
    response.text().then(text => { errorContainer.innerText += text });
    document.getElementsByTagName("body").item(0).appendChild(errorContainer);
    throw new Error("Something went wrong");
}

const importPrinters = (printers, titleStatus) => {
    removeCancelButtonIfExists();
    elementList.innerHTML = "";
    if (printers.length === 0) {
        elementTitleList.innerText = "А принтеров тютю!";
        return;
    }
    elementTitleList.innerText = titleStatus;
    for (let printer of printers) {
        let htmlButtonElement = document.createElement('button');
        htmlButtonElement.setAttribute('type', 'button');
        htmlButtonElement.setAttribute('class', 'list-group-item list-group-item-action');
        htmlButtonElement.setAttribute('data-bs-toggle', "modal")
        htmlButtonElement.setAttribute('data-bs-target', "#printerInfoModal")
        if (printer.model === undefined) {
            htmlButtonElement.innerText = printer._id;
        } else {
            htmlButtonElement.innerText = printer.model;
        }
        htmlButtonElement.addEventListener('click', function () {
            titleModal.innerText = printer.model;
            for (const key in printer) {
                if (key === "model") {
                    continue;
                }
                tableOptions.appendChild(addNewRowInTable(key, printer));
            }
        });
        elementList.appendChild(htmlButtonElement);
    }
};

const addCancelButton = () => {
    removeCancelButtonIfExists();
    const cancelButton = document.createElement("button");
    cancelButton.setAttribute("type", "button");
    cancelButton.setAttribute('class', "btn btn-outline-danger mt-3");
    cancelButton.setAttribute("id", "button-cancel");
    cancelButton.innerText = "Галя, отменяем все фильтры!";
    cancelButton.addEventListener("click", function () {
        fetch("/api/v1/printers/all", {
            method: "GET"
        })
            .then(response => checkResponse(response))
            .then(json => importPrinters(json, "Принтеры из бд:"));
        elementCoreContainer.removeChild(cancelButton);
    });
    elementCoreContainer.appendChild(cancelButton);
};

const removeCancelButtonIfExists = () => {
    const cancelButton = document.getElementById("button-cancel");
    if(cancelButton != null) {
        elementCoreContainer.removeChild(cancelButton);
    }
}

const addNewRowInTable = (key, printer) => {
    const rowElement = document.createElement("div");
    rowElement.setAttribute("class", "row align-items-start");

    const divKey = document.createElement("div");
    divKey.setAttribute("class", "col");
    const divValue = document.createElement("div");
    divValue.setAttribute("class", "col");

    divKey.innerText = key;
    divValue.innerText = printer[key];

    rowElement.appendChild(divKey);
    rowElement.appendChild(divValue);
    return rowElement;
}
