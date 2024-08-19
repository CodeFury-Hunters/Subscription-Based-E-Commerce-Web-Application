function toggleSidebar() {
    var sidebar = document.getElementById('sidebar');
    var overlay = document.getElementById('overlay');
    if (sidebar.style.width === '250px') {
        sidebar.style.width = '0';
        overlay.style.display = 'none';
    } else {
        sidebar.style.width = '250px';
        overlay.style.display = 'block';
    }
}

// Define card data
const featuredItems = [
    {
        id: 'vegetables-fruits',
        imgSrc: 'img/vegetables.jpg',  // Update to include folder path
        altText: 'Vegetables/Fruits',
        title: 'Vegetables/Fruits',
        description: 'Fresh and healthy vegetables and fruits for your meals.',
        link: '#'
    },
    {
        id: 'snacks',
        imgSrc: 'img/snacks.jpg',  // Update to include folder path
        altText: 'Snacks',
        title: 'Snacks',
        description: 'Delicious snacks for any time of the day.',
        link: '#'
    },
    {
        id: 'organic-foods',
        imgSrc: 'img/organic-foods.jpg',  // Update to include folder path
        altText: 'Organic Foods',
        title: 'Organic Foods',
        description: 'Pure and organic foods for a healthy lifestyle.',
        link: '#'
    }
];

const readyToEatItems = [
    {
        id: 'breakfast',
        imgSrc: 'img/breakfast.jpg',  // Update to include folder path
        altText: 'Breakfast',
        title: 'Breakfast',
        description: 'Start your day with a nutritious breakfast.',
        link: '#'
    },
    {
        id: 'lunch',
        imgSrc: 'img/lunch.jpg',  // Update to include folder path
        altText: 'Lunch',
        title: 'Lunch',
        description: 'Enjoy a satisfying and convenient lunch.',
        link: '#'
    },
    {
        id: 'dinner',
        imgSrc: 'img/dinner.jpg',  // Update to include folder path
        altText: 'Dinner',
        title: 'Dinner',
        description: 'Delicious dinner options for any evening.',
        link: '#'
    }
];

// Function to render cards
function renderCards(sectionId, items) {
    const container = document.getElementById(sectionId);
    let cardsHTML = '';
    
    items.forEach(item => {
        cardsHTML += `
            <div class="card" id="${item.id}">
                <img src="${item.imgSrc}" alt="${item.altText}">
                <div class="card-content">
                    <h3>${item.title}</h3>
                    <p>${item.description}</p>
                    <a href="${item.link}" class="btn">Shop Now</a>
                </div>
            </div>
        `;
    });
    
    container.innerHTML = cardsHTML;
}

// Render cards
document.addEventListener('DOMContentLoaded', () => {
    renderCards('featured-items-container', featuredItems);
    renderCards('ready-to-eat-container', readyToEatItems);
});

function togglePopup(cardId) {
    const popup = document.querySelector(`#${cardId} .popup`);
    popup.style.display = popup.style.display === 'block' ? 'none' : 'block';
}

function deleteCard(cardId) {
    const card = document.getElementById(cardId);
    card.remove();
}

function addItem(cardId) {
    // Logic to add new items can be implemented here
    alert('Add new items functionality is not implemented yet.');
}

function renderCards(sectionId, items) {
    const container = document.getElementById(sectionId);
    let cardsHTML = '';
    
    items.forEach(item => {
        cardsHTML += `
            <div class="card" id="${item.id}" style="position: relative;">
                <button class="options-btn" onclick="togglePopup('${item.id}')">⋮</button>
                <div class="popup">
                    <a onclick="deleteCard('${item.id}')">Delete the card</a>
                    <a onclick="addItem('${item.id}')">Add new items</a>
                </div>
                <img src="${item.imgSrc}" alt="${item.altText}">
                <div class="card-content">
                    <h3>${item.title}</h3>
                    <p>${item.description}</p>
                    <a href="${item.link}" class="btn">Shop Now</a>
                </div>
            </div>
        `;
    });
    
    container.innerHTML = cardsHTML;

    // Close any open popups if clicked outside
    document.addEventListener('click', (event) => {
        if (!event.target.matches('.options-btn')) {
            const popups = document.querySelectorAll('.popup');
            popups.forEach(popup => {
                popup.style.display = 'none';
            });
        }
    });
}

// Render cards
document.addEventListener('DOMContentLoaded', () => {
    renderCards('featured-items-container', featuredItems);
    renderCards('ready-to-eat-container', readyToEatItems);
});

   // Function to toggle the add item popup
function toggleAddItemPopup() {
    const popup = document.getElementById('add-item-popup');
    popup.style.display = popup.style.display === 'block' ? 'none' : 'block';
}

// Handle form submission to add a new item
document.getElementById('add-item-form').addEventListener('submit', (event) => {
    event.preventDefault();

    const title = document.getElementById('item-title').value;
    const imgSrc = document.getElementById('item-img').value;
    const description = document.getElementById('item-description').value;

    const newItem = {
        id: title.toLowerCase().replace(/\s+/g, '-'),
        imgSrc: imgSrc,
        altText: title,
        title: title,
        description: description,
        link: '#'
    };

    featuredItems.push(newItem);
    renderCards('featured-items-container', featuredItems);
    toggleAddItemPopup();
});

// Adjust existing functions if needed
function renderCards(sectionId, items) {
    const container = document.getElementById(sectionId);
    let cardsHTML = '';
    
    items.forEach(item => {
        cardsHTML += `
            <div class="card" id="${item.id}" style="position: relative;">
                <button class="options-btn" onclick="togglePopup('${item.id}')">⋮</button>
                <div class="popup">
                    <a onclick="deleteCard('${item.id}')">Delete the card</a>
                    <a onclick="addItem('${item.id}')">Add new items</a>
                </div>
                <img src="${item.imgSrc}" alt="${item.altText}">
                <div class="card-content">
                    <h3>${item.title}</h3>
                    <p>${item.description}</p>
                    <a href="${item.link}" class="btn">Shop Now</a>
                </div>
            </div>
        `;
    });
    
    container.innerHTML = cardsHTML;

    // Close any open popups if clicked outside
    document.addEventListener('click', (event) => {
        if (!event.target.matches('.options-btn')) {
            const popups = document.querySelectorAll('.popup');
            popups.forEach(popup => {
                popup.style.display = 'none';
            });
        }
    });
}
// Function to load HTML fragments
function loadHTML(id, url) {
    fetch(url)
        .then(response => response.text())
        .then(data => document.getElementById(id).innerHTML = data)
        .catch(error => console.log('Error loading HTML:', error));
}

// Load header, sidebar, and footer
loadHTML('header-container', 'header.html');
loadHTML('sidebar-container', 'sidebar.html');
loadHTML('footer-container', 'footer.html');

// Existing toggleSidebar function
function toggleSidebar() {
    var sidebar = document.getElementById('sidebar');
    var overlay = document.getElementById('overlay');
    if (sidebar.style.width === '250px') {
        sidebar.style.width = '0';
        overlay.style.display = 'none';
    } else {
        sidebar.style.width = '250px';
        overlay.style.display = 'block';
    }
}

