document.addEventListener("DOMContentLoaded", () => {
    fetchOrders();
});

let orders = [
    {
        id: 1,
        date: "2024-08-17",
        items: [
            { name: "Laptop", quantity: 1, price: 1200, image: "https://via.placeholder.com/150" },
            { name: "Mouse", quantity: 2, price: 50, image: "https://via.placeholder.com/150" },
        ],
        status: "Delivered",
    },
    {
        id: 2,
        date: "2024-08-16",
        items: [
            { name: "Headphones", quantity: 1, price: 200, image: "https://via.placeholder.com/150" },
        ],
        status: "Pending",
    },
    {
        id: 3,
        date: "2024-08-16",
        items: [
            { name: "Headphones", quantity: 1, price: 200, image: "https://via.placeholder.com/150" },
        ],
        status: "Cancelled",
    },
    {
        id: 4,
        date: "2024-08-16",
        items: [
            { name: "Headphones", quantity: 1, price: 200, image: "https://via.placeholder.com/150" },
        ],
        status: "Processing",
    },
];

function fetchOrders() {
    displayOrders(orders);
}

function displayOrders(orders) {
    const ordersContainer = document.getElementById("orders");
    ordersContainer.innerHTML = '';

    orders.forEach(order => {
        order.items.forEach(item => {
            const orderElement = document.createElement("div");
            orderElement.className = "order";

            const itemImage = document.createElement("img");
            itemImage.src = item.image;
            itemImage.alt = item.name;

            const orderDetails = document.createElement("div");
            orderDetails.className = "order-details";
            orderDetails.innerHTML = `
                <h5>${item.name}</h5> 
                <p>Quantity: ${item.quantity}</p>
                <p class="price">Price: Rs.${item.price}</p> 
                <p>Date: ${order.date}</p>
                <p class="status-${order.status.toLowerCase()}">Status: ${order.status}</p> 
            `;

            orderElement.appendChild(itemImage);
            orderElement.appendChild(orderDetails);
            ordersContainer.appendChild(orderElement);
        });
    });
}

// Function to simulate adding multiple new items dynamically
function addNewItems(items) {
    const newOrder = {
        id: orders.length + 1, 
        date: new Date().toISOString().split('T')[0], 
        items: items,
        status: "Processing",
    };
    orders.push(newOrder);
    displayOrders(orders);
}

//  dynamically adding multiple new items 
setTimeout(() => {
    const newItems = [
        { name: "Keyboard", quantity: 1, price: 100, image: "https://via.placeholder.com/150" },
        { name: "Mousepad", quantity: 1, price: 25, image: "https://via.placeholder.com/150" },
        { name: "Monitor", quantity: 1, price: 300, image: "https://via.placeholder.com/150" },
    ];
    addNewItems(newItems);
}, 5000);
function toggleSidebar() {
    const sidebar = document.getElementById("sidebar");
    const overlay = document.getElementById("overlay");
    if (sidebar.style.left === "-250px") {
        sidebar.style.left = "0";
        overlay.style.display = "block";
    } else {
        sidebar.style.left = "-250px";
        overlay.style.display = "none";
    }
}