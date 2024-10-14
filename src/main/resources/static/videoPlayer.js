let isPlaying = false;
let progress = 0;
let interval;

const progressBar = document.getElementById('progressBar');
const playPauseBtn = document.getElementById('playPauseBtn');

progressBar.addEventListener('input', (event) => {
    const duration = document.getElementById('vidDuration').value;
    clearInterval(interval);
    progress = (event.target.value / 100) * duration;
    progressChanged(progress);
});

playPauseBtn.addEventListener('click', () => {
    if (isPlaying) {
        clearInterval(interval);
        playPauseBtn.textContent = 'Play';
    } else {
        interval = setInterval(updateProgress, 100);
        playPauseBtn.textContent = 'Pause';
    }
    isPlaying = !isPlaying;
});

function updateProgress() {
    const duration = document.getElementById('vidDuration').value;
    if (progress >= duration) {
        clearInterval(interval);
        isPlaying = false;
        playPauseBtn.textContent = 'Play';
        progress = 0;
        return;
    }

    progress += 0.1;
    const progressPercent = (progress / duration) * 100;
    progressBar.value = progressPercent;
    
    progressChanged(progress);
}

function progressChanged(progress) {
    const sliderValue = progress / document.getElementById('vidDuration').value * 100;
    const itemInRange = findIndexInRange(sliderValue, sliderValues);
    const progressNorm = sliderValue - 100 / uploadedImages1.length * itemInRange;
    const zoomValue = progressNorm * uploadedImages1.length / 100

    const zoomValueInput = document.getElementById('zoomValue');

    displayImage(itemInRange, zoomValue * zoomValueInput.value);
}

function findIndexInRange(value, arr) {
    const sortedArr = arr.slice().sort((a, b) => a - b);

    if (value < sortedArr[0]) {
        return 0;
    }

    if (value >= sortedArr[sortedArr.length - 1]) {
        return sortedArr.length;
    }

    for (let i = 1; i < sortedArr.length; i++) {
        if (value >= sortedArr[i - 1] && value < sortedArr[i]) {
            return i;
        }
    }
    
    return -1;
}

function zoomImage() {
    var zoomLevel = document.getElementById('zoomRange').value;
    var imgElement = document.getElementById('dynamic-image');
    imgElement.style.transform = 'scale(' + zoomLevel / 100 + ')';
}