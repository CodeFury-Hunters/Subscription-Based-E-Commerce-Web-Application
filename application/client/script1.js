// Function to toggle sidebar visibility
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
        imgSrc: 'img/vegetables.jpg',
        altText: 'Vegetables/Fruits',
        title: 'Vegetables/Fruits',
        description: 'Fresh and healthy vegetables and fruits for your meals.',
        link: 'veg.html' // Updated link
    },
    {
        id: 'snacks',
        imgSrc: 'img/snacks.jpg',
        altText: 'Snacks',
        title: 'Snacks',
        description: 'Delicious snacks for any time of the day.',
        link: 'snack.html' // Updated link
    },
    {
        id: 'organic-foods',
        imgSrc: 'img/organic-foods.jpg',
        altText: 'Organic Foods',
        title: 'Organic Foods',
        description: 'Pure and organic foods for a healthy lifestyle.',
        link: 'org.html' // Updated link
    }
];

const readyToEatItems = [
    {
        id: 'breakfast',
        imgSrc: 'img/breakfast.jpg',
        altText: 'Breakfast',
        title: 'Breakfast',
        description: 'Start your day with a nutritious breakfast.',
        link: 'break.html' // Updated link
    },
    {
        id: 'lunch',
        imgSrc: 'img/lunch.jpg',
        altText: 'Lunch',
        title: 'Lunch',
        description: 'Enjoy a satisfying and convenient lunch.',
        link: 'lunch.html' // Updated link
    },
    {
        id: 'dinner',
        imgSrc: 'img/dinner.jpg',
        altText: 'Dinner',
        title: 'Dinner',
        description: 'Delicious dinner options for any evening.',
        link: 'dinner.html' // Updated link
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
