async function getUsers() {


    const response = await fetch("api/admin");

    if (response.ok) {
        let json = await response.json()
            .then(data => replaceTable(data));
    } else {
        alert("Ошибка HTTP: " + response.status);
    }

    function replaceTable(data) {
        const placement = document.getElementById("usersTablePlacement")
        placement.innerHTML = "";
        data.forEach(({id, firstName, lastName, age, email, password, roleSet}) => {
            let userRoles = "";
            roleSet.forEach((role) => {
                userRoles = userRoles + role.roleName.split("_")[1] + " ";
            })
            const element = document.createElement("tr");
            element.innerHTML = `
            <th scope="row">${id}</th>
            <td>${firstName}</td>
            <td>${lastName}</td>
            <td>${age}</td>
            <td>${email}</td>
            <td>${userRoles}</td>
            <td>
                <button type="button" class="btn btn-info text-white" data-bs-userId=${id}
                    data-bs-userFirstName=${firstName} data-bs-userLastName=${lastName} data-bs-userAge=${age}
                    data-bs-userEmail=${email}  data-bs-userPassword=${password}   data-bs-toggle="modal"
                    data-bs-target="#ModalEdit">Edit</button>
            </td>
            <td>
                <button type="button" class="btn btn-danger" data-bs-userId=${id}
                    data-bs-userFirstName=${firstName} data-bs-userLastName=${lastName} data-bs-userAge=${age}
                    data-bs-userEmail=${email} data-bs-userPassword=${password} data-bs-userR=${userRoles}  data-bs-toggle="modal"
                    data-bs-target="#ModalDelete">Delete</button>
            </td>         
            `
            placement.append(element);
        })
    }


}