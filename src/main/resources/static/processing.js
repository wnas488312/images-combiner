const proceedButton = document.getElementById('proceed');

proceedButton.addEventListener('click', () => {
    fetch('/videos/initialize', { method: 'POST' })
        .then(response => response.json())
        .then(data => console.log('id: ' + data.id));
});