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
    console.log(progress.toFixed(1) + ' seconds');
    console.log('progress: ' + progress / document.getElementById('vidDuration').value * 100 );
    console.log('images: ' + sliderValues);
}