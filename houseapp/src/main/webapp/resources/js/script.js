function deleteAPI(url) {
    if (confirm("Nhấn OK để thực hiện xoá!" )) {
        fetch(url, {
            method: 'delete'
        }).then(res => {
            if (res.status === 204) {
                location.reload();
            } else {
                alert("ERROR");
            }
        }).catch(error => {
            
            alert("ERROR: " + error);
        });
    }
}

