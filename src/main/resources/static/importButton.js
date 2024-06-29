const importInput = document.getElementById("import-input");
const importButton = document.getElementById("import-button");

importButton.addEventListener("click", function () {
    importInput.click();
});

importInput.addEventListener("change", async function () {
    const url = "/api/v1/printers/upload";
    let formData = new FormData();
    formData.append("file", importInput.files[0]);
    try {
        let responsePromise = await fetch(url, {
            method: "POST",
            body: formData
        });
        console.log(responsePromise.status)
    } catch (e) {
        console.error(e);
    }

})