function fetchDistricts(url,id) {
    fetch(url + id)
        .then(response => response.json())
        .then(districts => {
            console.log("hello")
            let districtSelect = document.getElementById('districtId');
            districtSelect.innerHTML = '<option value="">Select District</option>';
            districts.forEach(district => {
                
                let option = document.createElement('option');
                option.value = district.id;
                option.text = district.name;
                districtSelect.appendChild(option);
            });
            // Clear the ward select box when province changes
            document.getElementById('wardId').innerHTML = '<option value="">Select Ward</option>';
        })
        .catch(error => console.error('Error fetching districts:', error));
}

function fetchWards(url, id) {
    fetch(url + id)
        .then(response => response.json())
        .then(wards => {
            let wardSelect = document.getElementById('wardId');
            wardSelect.innerHTML = '<option value="">Select Ward</option>';
            wards.forEach(ward => {
                let option = document.createElement('option');
                option.value = ward.id;
                option.text = ward.name;
                wardSelect.appendChild(option);
            });
        })
        .catch(error => console.error('Error fetching wards:', error));
}
