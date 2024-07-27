const importInput = document.getElementById("import-document-input");
const importButton = document.getElementById("import-document-button");
const elementList = document.getElementById("list-printers");
const printerInfoModal = document.getElementById("printerInfoModal");
const titleModal = document.getElementById("printer-info-modal-label");
const tableOptions = document.getElementById("printer-option-table");

printerInfoModal.addEventListener('hidden.bs.modal', function () {
    tableOptions.innerHTML = "";
})

importButton.addEventListener("click", function () {
    importInput.click();
});

importInput.addEventListener("change", async function () {
    const url = "/api/v1/printers/importDocument";
    let formData = new FormData();
    formData.append("file", importInput.files[0]);

    await fetch(url, {
        method: "POST",
        body: formData
    }).then(response => checkResponse(response))
        .then(json => importPrinters(json))
})

fetch("api/v1/printers/all", {
    method: "GET"
}).then(response => checkResponse(response))
    .then(json => importPrinters(json));

const checkResponse = (response) => {
    if (response.ok) {
        return response.json();
    }
    const errorContainer = document.createElement("div");
    errorContainer.setAttribute("class", "alert alert-danger");
    errorContainer.innerText = "Произошла ябучая ошибка отправь мне ошибку из консоли в F12";
    document.getElementsByTagName("body").item(0).appendChild(errorContainer);
    throw new Error("Something went wrong");
}

const importPrinters = (printers) => {
    for (let printer of printers) {
        let htmlButtonElement = document.createElement('button');
        htmlButtonElement.setAttribute('type', 'button');
        htmlButtonElement.setAttribute('class', 'list-group-item list-group-item-action');
        htmlButtonElement.setAttribute('data-bs-toggle', "modal")
        htmlButtonElement.setAttribute('data-bs-target', "#printerInfoModal")
        htmlButtonElement.innerText = printer.model;
        htmlButtonElement.addEventListener('click',  function () {
            titleModal.innerText = printer.model;
            for (const key in printer) {
                if(key === "model") {
                    continue;
                }
                tableOptions.appendChild(addNewRowInTable(key, printer));
            }
        });
        elementList.appendChild(htmlButtonElement);
    }
};

// const fillPrinterInfoInModal = (printer) => {
//     titleModal.innerText = printer.model;
//
//     for (const key in printer) {
//         if(key === "modal") {
//             continue;
//         }
//         addNewRowInTable(key, printer);
//     }
//
// }

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













