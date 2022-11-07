const addForm = document.getElementById("addForm");
$(document).on('click', '#addButton', async function () {

    const formData = new FormData(addForm);
    const object = {
        roleSet:[]
    };

    formData.forEach((value, key) => {
        if (key === "rolesId"){

            const roleId = value.split(" ")[0];
            const roleName = value.split(" ")[1];
            const role = {
                id : roleId,
                name : "ROLE_" + roleName
            };
            object.roleSet.push(role);
        } else {
            object[key] = value;
        }
    });
    console.log(object)

    fetch("api/admin", {
        method: "POST",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(object)
    })
        .then(() => getUsers())
        .then(() => addForm.reset());




})