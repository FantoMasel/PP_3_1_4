async function showAuthUser() {


    const response = await fetch("api/user");

    if (response.ok) {
        let json = await response.json()
            .then(data => replaceTable(data));
    } else {
        alert("Ошибка HTTP: " + response.status);
    }

    function replaceTable(data) {
        let userRoles = "";
        data.roleSet.forEach((role) => {
            userRoles = userRoles + role.roleName.split("_")[1] + " ";
            const placement = document.getElementById("authUserPanel")
            placement.innerHTML = "";


            const element = document.createElement("tr");
            element.innerHTML = `
            <th scope="row">${data.id}</th>
            <td>${data.firstName}</td>
            <td>${data.lastName}</td>
            <td>${data.age}</td>
            <td>${data.email}</td>
            <td>${userRoles}</td>
                   
            `
            placement.append(element);
        })
    }


}