const formDelete = document.getElementById('formDelete')


$(document).on('click', '#submit-delete', async function () {
    fetch("api/admin/" + document.forms["formDelete"].elements["id"].value, {
        method: "DELETE"
    })
        .then(() => getUsers());
    $("#ModalDelete").modal("hide");
    formDelete.reset();
})