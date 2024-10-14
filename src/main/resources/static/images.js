const dropZone = document.getElementById('dropZone');
const dropMessage = document.getElementById('dropMessage');
const imagesContainer = document.getElementById('imagesContainer');
const proceedButton = document.getElementById('applyVideoLength');

let uploadedImages = [];
let uploadedImages1 = [];

let imagesCount = 0;

dropZone.addEventListener('dragover', (e) => {
    e.preventDefault();
    dropZone.classList.add('dragover');
});

dropZone.addEventListener('dragleave', () => {
    dropZone.classList.remove('dragover');
});

dropZone.addEventListener('drop', (e) => {
    e.preventDefault();
    dropZone.classList.remove('dragover');

    if (e.dataTransfer.files.length > 0) {
        handleFiles(e.dataTransfer.files);
    }
});

dropZone.addEventListener('click', () => {
    const fileInput = document.createElement('input');
    fileInput.type = 'file';
    fileInput.accept = 'image/*';
    fileInput.multiple = true;
    fileInput.click();

    fileInput.addEventListener('change', () => {
        handleFiles(fileInput.files);
    });
});

proceedButton.addEventListener('click', () => {
    displayImage(0, 0);
    createMarkers();
});

function handleFiles(files) {      
    const validFiles = Array.from(files)
        .filter(file => file.type.startsWith('image/'));
    
    validFiles.forEach(file => {
        if (!file.name.startsWith("download")) {
            const reader = new FileReader();
            reader.onload = (event) => {
                const imageWrapper = createImageWrapper(event.target.result);
                imagesContainer.appendChild(imageWrapper);
                imagesCount += 1;
            };
            reader.readAsDataURL(file);
        }
    });

    uploadedImages = [...uploadedImages, ...validFiles];

    if (uploadedImages.length > 0) {
        dropMessage.style.display = 'none';
    }
}

function createImageWrapper(imageSrc) {
    const imageWrapper = document.createElement('div');
    imageWrapper.classList.add('image-wrapper');
    imageWrapper.setAttribute('draggable', true);

    const img = document.createElement('img');
    img.src = imageSrc;
    uploadedImages1 = [...uploadedImages1, imageSrc];

    const removeBtn = document.createElement('button');
    removeBtn.classList.add('remove-btn');
    const removeIcon = document.createElement('img');
    removeIcon.src = 'remove-icon.png';
    removeBtn.appendChild(removeIcon);

    removeBtn.addEventListener('click', () => {
        imageWrapper.remove();
    });

    imageWrapper.appendChild(img);
    imageWrapper.appendChild(removeBtn);

    imageWrapper.addEventListener('dragstart', handleDragStart);
    imageWrapper.addEventListener('dragover', handleDragOver);
    imageWrapper.addEventListener('drop', handleDrop);
    imageWrapper.addEventListener('dragend', handleDragEnd);

    return imageWrapper;
}

let draggedItem = null;
let draggedIndex = null;

function handleDragStart(e) {
    draggedItem = this;
    draggedIndex = Array.from(imagesContainer.children).indexOf(this);
    setTimeout(() => {
        this.style.display = 'none';
    }, 0);
}

function handleDragOver(e) {
    e.preventDefault();
    const overItem = this;
    const children = Array.from(imagesContainer.children);
    const overIndex = children.indexOf(overItem);

    if (overItem !== draggedItem) {
        if (overIndex > draggedIndex) {
            imagesContainer.insertBefore(draggedItem, overItem.nextSibling);
        } else {
            imagesContainer.insertBefore(draggedItem, overItem);
        }
        draggedIndex = overIndex;
    }
}

function handleDrop(e) {
    e.preventDefault();
}

function handleDragEnd() {
    setTimeout(() => {
        draggedItem.style.display = 'block';
        draggedItem = null;
        draggedIndex = null;
    }, 0);
}

function displayImage(imageId, zoomValue) {
    var imageUrl = uploadedImages1[imageId];
    var imgElement = document.getElementById('dynamic-image');
    imgElement.src = imageUrl;

    let initialZoom = 1.0;
    if (zoomValue < 0.0) {
        const zoomValueInput = document.getElementById('zoomValue');
        initialZoom = 1.0 + Math.abs(zoomValueInput.value);
    }

    imgElement.style.transform = 'scale(' + (initialZoom + zoomValue) + ')';

    var container = document.getElementById('image-container');
    container.style.display = 'block';

    var progressContainer = document.getElementById('progress-container');
    progressContainer.style.display = 'block';
}