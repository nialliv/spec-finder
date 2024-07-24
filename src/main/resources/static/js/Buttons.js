import { setPrinters } from 'ListElements.js';

const importInput = document.getElementById("import-document-input");
const buttons = document.getElementById("import-document-button");

buttons.addEventListener("click", function () {
    importInput.click();
});

importInput.addEventListener("change", async function () {
    const url = "/api/v1/printers/upload";
    let formData = new FormData();
    formData.append("file", importInput.files[0]);

    await fetch(url, {
        method: "POST",
        body: formData
    }).then(response => {
        if (response.ok) {
            console.log(response.ok);
            return response.json();
        }
        throw new Error("Something went wrong");
    }).then(json => setPrinters(json))
})