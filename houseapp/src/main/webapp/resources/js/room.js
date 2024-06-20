function fetchRooms(url, postId) {
    fetch(url + postId)
        .then(response => response.json())
        .then(rooms => {
            let roomSelect = document.getElementById('roomId');
            roomSelect.innerHTML = '<option value="">Chọn phòng</option>';
            rooms.forEach(room => {
                let option = document.createElement('option');
                option.value = room.id;
                option.text = room.name + " " +  room.userId.username +"-"+room.userId.role;
                roomSelect.appendChild(option);
            });
        })
        .catch(error => console.error('Lỗi khi tải phòng:', error));
}
