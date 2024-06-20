function fetchPosts(url, userId) {
    fetch(url + userId)
            .then(response => response.json())
            .then(posts => {
                console.log(posts);
                let postSelect = document.getElementById('postId');
                postSelect.innerHTML = '<option value="">Chọn bài đăng</option>';
                posts.forEach(p => {
                    let option = document.createElement('option');
                    option.value = p.id;
                    option.text = p.title + " " + p.userId.role;
                    postSelect.appendChild(option);
                });
            })
            .catch(error => console.error('Lỗi khi tải bài đăng:', error));
}

