document.addEventListener("DOMContentLoaded", () => {
    fetchDeliveries();
});

let deliveries = [
    {
        id: 1,
        customerName: "John Doe",
        orderName: "Laptop",
        deliveryStatus: "Delivered",
        deliveryTime: "17/08/2024 10:00 AM",
        paymentStatus: "Paid",
        address: "123 Main St, Springfield"
    },
    {
        id: 2,
        customerName: "Jane Smith",
        orderName: "Smartphone",
        deliveryStatus: "In Transit",
        deliveryTime: "17/08/2024 11:00 AM",
        paymentStatus: "Pending",
        address: "456 Elm St, Springfield"
    },
    {
        id: 3,
        customerName: "Alice Johnson",
        orderName: "Tablet",
        deliveryStatus: "Scheduled",
        deliveryTime: "17/08/2024 12:00 PM",
        paymentStatus: "Failed",
        address: "789 Oak St, Springfield"
    },
];

function fetchDeliveries() {
    displayDeliveries(deliveries);
}

function displayDeliveries(deliveries) {
    const deliveriesContainer = document.getElementById("deliveries");
    deliveriesContainer.innerHTML = '';

    // Create the header row
    const headerRow = document.createElement("div");
    headerRow.className = "delivery-header";
    headerRow.innerHTML = `
        <p>Customer Name</p>
        <p>Order Name</p>
        <p>Status</p>
        <p>Delivery Time</p>
        <p>Payment Status</p>
        <p>Address</p>
    `;
    deliveriesContainer.appendChild(headerRow);

    // Create and append each delivery item
    deliveries.forEach(delivery => {
        const deliveryElement = document.createElement("div");
        deliveryElement.className = "delivery";

        const deliveryDetails = document.createElement("div");
        deliveryDetails.className = "delivery-details";
        deliveryDetails.innerHTML = `
            <p class="bold">${delivery.customerName}</p>
            <p class="bold">${delivery.orderName}</p>
            <p class="delivery-status ${delivery.deliveryStatus.toLowerCase().replace(' ', '-')}">${delivery.deliveryStatus}</p>
            <p>${delivery.deliveryTime}</p>
            <p class="payment-status ${delivery.paymentStatus.toLowerCase()}">${delivery.paymentStatus}</p>
            <p>${delivery.address}</p>
        `;

        deliveryElement.appendChild(deliveryDetails);
        deliveriesContainer.appendChild(deliveryElement);
    });
}

// Function to dynamically add a new delivery
function addDelivery(newDelivery) {
    deliveries.push(newDelivery);
    displayDeliveries(deliveries);
}

// Function to get the current date and time in 12-hour format
function getCurrentDateTime() {
    const now = new Date();
    const options = {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit',
        hour12: true,
    };
    return now.toLocaleString('en-US', options).replace(',', '');
}

// Function to generate a random delivery status
function getRandomStatus() {
    const statuses = ["Delivered", "In Transit", "Scheduled", "Failed"];
    return statuses[Math.floor(Math.random() * statuses.length)];
}

// Function to generate a random payment status
function getRandomPaymentStatus() {
    const statuses = ["Paid", "Pending", "Failed"];
    return statuses[Math.floor(Math.random() * statuses.length)];
}

// Example usage: dynamically adding multiple new deliveries
const newDeliveries = [
    {
        id: 4,
        customerName: "Emily Davis",
        orderName: "Headphones",
        deliveryStatus: "Delivered",
        deliveryTime: getCurrentDateTime(),
        paymentStatus: "Paid",
        address: "123 Pine St, Springfield"
    },
    {
        id: 5,
        customerName: "Michael Brown",
        orderName: "Smartwatch",
        deliveryStatus: getRandomStatus(),
        deliveryTime: getCurrentDateTime(),
        paymentStatus: getRandomPaymentStatus(),
        address: "234 Maple St, Springfield"
    },
    {
        id: 6,
        customerName: "Linda White",
        orderName: "Camera",
        deliveryStatus: getRandomStatus(),
        deliveryTime: getCurrentDateTime(),
        paymentStatus: getRandomPaymentStatus(),
        address: "345 Cedar St, Springfield"
    }
];

// Add the new deliveries after 2 seconds
setTimeout(() => {
    newDeliveries.forEach(delivery => addDelivery(delivery));
}, 2000);

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