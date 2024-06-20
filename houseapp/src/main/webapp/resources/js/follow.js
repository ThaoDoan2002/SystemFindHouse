function fetchLandlords(url, userId) {
    fetch(url + userId)
            .then(response => response.json())
            .then(posts => {
                console.log(posts);
                let landlordSelect = document.getElementById('landlordId');
                landlordSelect.innerHTML = '<option value="">Chọn chủ trọ</option>';
                posts.forEach(l => {
                    let option = document.createElement('option');
                    option.value = l.id;
                    option.text = l.username + " " + l.role;
                    landlordSelect.appendChild(option);
                });
            })
            .catch(error => console.error('Lỗi khi tải chủ trọ:', error));
}

