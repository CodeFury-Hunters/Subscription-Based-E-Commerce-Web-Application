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
