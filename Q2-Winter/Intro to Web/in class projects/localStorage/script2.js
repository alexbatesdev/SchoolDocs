const howdy = document.getElementById('howdy');

const getStuff = () => {
    let my_stuff = localStorage.getItem('stuff_in_local_storage');
    howdy.innerHTML = `Howdy ${my_stuff}`
}

getStuff()