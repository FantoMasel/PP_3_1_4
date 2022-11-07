const formEdit = document.getElementById("formEdit");
$(document).on('click', '#submit-edit', async function () {

    const formData = new FormData(formEdit);
    const object = {
        roleSet: []
    };

    formData.forEach((value, key) => {
        if (key === "rolesId") {

            const roleId = value.split(" ")[0];
            const roleName = value.split(" ")[1];
            const role = {
                id: roleId,
                name: "ROLE_" + roleName
            };
            object.roleSet.push(role);
        } else {
            object[key] = value;
        }
    });
    fetch("api/admin/" + document.forms["formEdit"].elements["id"].value, {
        method: "PUT",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(object)
    })
        .then(() => getUsers());
    $("#ModalEdit").modal("hide");
    formEdit.reset();
})