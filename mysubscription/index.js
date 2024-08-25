document.addEventListener("DOMContentLoaded", () => {
    fetchSubscriptions();
});

let subscriptions = [
    {
        id: 1,
        name: "Prime Membership",
        type: "Monthly",
        price: 15,
        image: "https://via.placeholder.com/150",
        status: "Active",
        createdTime: "17/08/2024 10:00 AM",
    },
    {
        id: 2,
        name: "Gym Membership",
        type: "Yearly",
        price: 300,
        image: "https://via.placeholder.com/150",
        status: "Active",
        createdTime: "17/08/2024 11:00 AM",
    },
    {
        id: 3,
        name: "Magazine Subscription",
        type: "Weekly",
        price: 5,
        image: "https://via.placeholder.com/150",
        status: "Pending",
        createdTime: "17/08/2024 12:00 PM",
    },
    {
        id: 4,
        name: "Cloud Storage",
        type: "Biweekly",
        price: 10,
        image: "https://via.placeholder.com/150",
        status: "Active",
        createdTime: "17/08/2024 01:00 PM",
    },
];

function fetchSubscriptions() {
    displaySubscriptions(subscriptions);
}

function displaySubscriptions(subscriptions) {
    const subscriptionsContainer = document.getElementById("subscriptions");
    subscriptionsContainer.innerHTML = '';

    subscriptions.forEach(subscription => {
        const subscriptionElement = document.createElement("div");
        subscriptionElement.className = "subscription";

        const itemImage = document.createElement("img");
        itemImage.src = subscription.image;
        itemImage.alt = subscription.name;

        const subscriptionDetails = document.createElement("div");
        subscriptionDetails.className = "subscription-details";
        subscriptionDetails.innerHTML = `
            <h5 class="bold">${subscription.name}</h5>
            <p>Type: ${subscription.type}</p>
            <p class="bold">Price: Rs.${subscription.price}</p>
            <p class="subscription-status ${subscription.status.toLowerCase()}">Status: ${subscription.status}</p>
            <p>Created: ${subscription.createdTime}</p>
        `;

        const subscriptionActions = document.createElement("div");
        subscriptionActions.className = "subscription-actions";

        if (subscription.status === "Active") {
            const cancelButton = document.createElement("button");
            cancelButton.className = "cancel-btn";
            cancelButton.innerText = "Cancel";
            cancelButton.addEventListener("click", () => cancelSubscription(subscription.id));
            subscriptionActions.appendChild(cancelButton);
        }

        subscriptionElement.appendChild(itemImage);
        subscriptionElement.appendChild(subscriptionDetails);
        subscriptionElement.appendChild(subscriptionActions);
        subscriptionsContainer.appendChild(subscriptionElement);
    });
}

function cancelSubscription(subscriptionId) {
    alert(`Subscription with ID ${subscriptionId} cancelled.`);
    const subscription = subscriptions.find(sub => sub.id === subscriptionId);
    if (subscription) {
        subscription.status = "Cancelled";
    }
    displaySubscriptions(subscriptions);
}

function getCurrentTime() {
    const now = new Date();
    const options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit', hour12: true };
    return now.toLocaleString('en-US', options).replace(',', '');
}

function addNewSubscription(subscription) {
    subscription.id = subscriptions.length + 1;
    subscription.createdTime = getCurrentTime();
    subscriptions.push(subscription);
    displaySubscriptions(subscriptions);
}

// Dynamically add new subscriptions after a delay
setTimeout(() => {
    const newSubscription1 = {
        name: "Streaming Service",
        type: "Monthly",
        price: 20,
        image: "https://via.placeholder.com/150",
        status: "Active",
    };
    addNewSubscription(newSubscription1);
}, 5000);

setTimeout(() => {
    const newSubscription2 = {
        name: "Online Course",
        type: "Yearly",
        price: 100,
        image: "https://via.placeholder.com/150",
        status: "Pending",
    };
    addNewSubscription(newSubscription2);
}, 10000);
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
