const submit_btn = document.getElementById('submit_btn');
const my_textbox = document.getElementById('my_textbox');

submit_btn.addEventListener('click', evt => {
    let stuff_in_box = my_textbox.value;
    localStorage.setItem('stuff_in_local_storage', stuff_in_box);
    my_textbox.value = "";
})