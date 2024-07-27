const rmRfButton = document.getElementById("rm-rf-button");

rmRfButton.addEventListener("click", function () {
    fetch("/api/v1/printers/deleteAll", {
        method: "DELETE",
    }).then(r => {
        if (r.ok) {
            console.log("Successfully deleted");
        }
    });
    document.getElementById("list-printers").innerHTML = "";
});