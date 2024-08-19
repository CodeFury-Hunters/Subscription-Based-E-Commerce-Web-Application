document.addEventListener("DOMContentLoaded", () => {
    fetchOrders();
});

let orders = [
    {
        id: 1,
        customerName: "John Doe",
        orderName: "Laptop",
        orderStatus: "Completed",
        paymentStatus: "Paid",
        hasSubscription: true,
        subscriptionType: "Monthly",
        createdTime: "17/08/2024 10:00 AM",
    },
    {
        id: 2,
        customerName: "Jane Smith",
        orderName: "Smartphone",
        orderStatus: "Pending",
        paymentStatus: "Unpaid",
        hasSubscription: false,
        subscriptionType: "",
        createdTime: "17/08/2024 11:00 AM",
    },
    {
        id: 3,
        customerName: "Alice Johnson",
        orderName: "Tablet",
        orderStatus: "Cancelled",
        paymentStatus: "Paid",
        hasSubscription: true,
        subscriptionType: "Yearly",
        createdTime: "17/08/2024 12:00 PM",
    },
];

function fetchOrders() {
    displayOrders(orders);
}

function displayOrders(orders) {
    const ordersContainer = document.getElementById("orders");
    ordersContainer.innerHTML = '';

    // Create the header row
    const headerRow = document.createElement("div");
    headerRow.className = "order-header";
    headerRow.innerHTML = `
        <p>Customer Name</p>
        <p>Order Name</p>
        <p>Status</p>
        <p>Payment Status</p>
        <p>Subscription Type</p>
        <p>Created Time</p>
    `;
    ordersContainer.appendChild(headerRow);

    // Create and append each order item
    orders.forEach(order => {
        const orderElement = document.createElement("div");
        orderElement.className = "order";

        const orderDetails = document.createElement("div");
        orderDetails.className = "order-details";
        orderDetails.innerHTML = `
            <p class="bold">${order.customerName}</p>
            <p class="bold">${order.orderName}</p>
            <p class="order-status ${order.orderStatus.toLowerCase()}">${order.orderStatus}</p>
            <p class="payment-status ${order.paymentStatus.toLowerCase()}">${order.paymentStatus}</p>
            ${order.hasSubscription ? `<p class="subscription-type">${order.subscriptionType}</p>` : '<p>No Subscription</p>'}
            <p>${order.createdTime}</p>
        `;

        orderElement.appendChild(orderDetails);
        ordersContainer.appendChild(orderElement);
    });
}

// Function to format date and time
function formatDate(date) {
    const options = { 
        day: '2-digit', 
        month: '2-digit', 
        year: 'numeric', 
        hour: '2-digit', 
        minute: '2-digit', 
        second: '2-digit', 
        hour12: true 
    };
    return new Intl.DateTimeFormat('en-US', options).format(date);
}

// Function to dynamically add a new order
function addOrder(newOrder) {
    newOrder.createdTime = formatDate(new Date()); // Set current time
    orders.push(newOrder);
    displayOrders(orders);
}

// Example usage: dynamically adding multiple new orders
const newOrders = [
    {
        id: 4,
        customerName: "Emily Davis",
        orderName: "Headphones",
        orderStatus: "Completed",
        paymentStatus: "Paid",
        hasSubscription: true,
        subscriptionType: "Biweekly",
    },
    {
        id: 5,
        customerName: "Michael Brown",
        orderName: "Smartwatch",
        orderStatus: "Pending",
        paymentStatus: "Unpaid",
        hasSubscription: false,
        subscriptionType: "",
    },
    {
        id: 6,
        customerName: "Sarah Wilson",
        orderName: "Camera",
        orderStatus: "Completed",
        paymentStatus: "Paid",
        hasSubscription: true,
        subscriptionType: "Yearly",
    },
];

// Add the new orders at different intervals
newOrders.forEach((order, index) => {
    setTimeout(() => addOrder(order), index * 3000); // Add orders every 3 seconds
});
