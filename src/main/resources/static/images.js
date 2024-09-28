const dropZone = document.getElementById('dropZone');
const dropMessage = document.getElementById('dropMessage');
const imagesContainer = document.getElementById('imagesContainer');
    
let uploadedImages = [];
    
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
    
    const files = e.dataTransfer.files;
    handleFiles(files);
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
    
function handleFiles(files) {
    const validFiles = Array.from(files).filter(file => file.type.startsWith('image/'));
    
    validFiles.forEach(file => {
        const reader = new FileReader();
        reader.onload = (event) => {
            const imageWrapper = document.createElement('div');
            imageWrapper.classList.add('image-wrapper');
    
            const img = document.createElement('img');
            img.src = event.target.result;

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
    
            imagesContainer.appendChild(imageWrapper);
        };
        reader.readAsDataURL(file);
    });
    
    uploadedImages = [...uploadedImages, ...validFiles];
    
    if (uploadedImages.length > 0) {
        dropMessage.style.display = 'none';
    }
}