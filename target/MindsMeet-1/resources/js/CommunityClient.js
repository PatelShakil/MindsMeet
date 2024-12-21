/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


// WebSocket instance
let socket = null;

let comId = $("#com").val();

let senderId = $("#sender").val();

document.addEventListener("DOMContentLoaded", () => {
    initializeWebSocket(comId);
});


// Initialize WebSocket connection
function initializeWebSocket(communityId) {
    if (!socket || socket.readyState !== WebSocket.OPEN) {
        socket = new WebSocket(`ws://localhost:8080/MindsMeet/community-messaging`);

        socket.onopen = () => {
            console.log("WebSocket connection established.");
            fetchLatestMessages(comId);
        };

        socket.onmessage = (event) => {
            handleIncomingMessage(event.data, communityId);
        };

        socket.onerror = (error) => {
            console.error("WebSocket encountered an error:", error);
        };

        socket.onclose = () => {
            console.log("WebSocket connection closed.");
        };
    }
}

// Handle incoming WebSocket messages
function handleIncomingMessage(data, communityId) {
    try {
        const message = JSON.parse(data);
        fetchLatestMessages(communityId); // Fetch the latest data using API
    } catch (error) {
        console.error("Error processing incoming WebSocket message:", error);
    }
}

// Fetch latest messages for the community
function fetchLatestMessages(communityId) {
    $.ajax({
        url: `http://localhost:8080/MindsMeet/api/community/get-messages/${communityId}`,
        method: "GET",
        success: function (messages) {
            updateMessageArea(messages);
        },
        error: function (error) {
            console.error("Error fetching messages:", error);
        }
    });
}
// Update the message area with fetched messages
function updateMessageArea(messages) {
    const messageArea = document.getElementById("messageArea");
    messageArea.innerHTML = ""; // Clear the current messages

    messages.forEach((message) => {
        const messageElement = createMessageElement(message);
        messageArea.appendChild(messageElement);

        if (message.userId.id == senderId) {
            $(`#msg_${message.id}`).addClass("bg-green-100 rounded-md rounded-br-none ml-auto");
        } else {
            $(`#msg_${message.id}`).addClass("bg-blue-100 rounded-md rounded-bl-none  mr-auto");
        }
    });

    // Auto-scroll to the bottom
    messageArea.scrollTop = messageArea.scrollHeight;
}

// Create a message DOM element
function createMessageElement(message) {
    const messageElement = document.createElement("div");
    messageElement.setAttribute("id", `msg_${message.id}`);

    messageElement.classList.add(
        "p-4", 
        "m-2", 
        "shadow", 
        "max-w-lg", 
        "flex", 
        "flex-col", 
        "gap-2"
    );

    const isSender = message.userId.id == senderId;

    // Template for the message content
    messageElement.innerHTML = `
        <div class="flex items-center w-full ${isSender ? 'mr-auto' : 'ml-auto'}">
            <img src="http://localhost:8080/MindsMeet/resources/uploads/images/${message.userId.profile}" 
                alt="Profile" 
                class="h-10 w-10 rounded-full shadow-sm" />
            <div class="ml-2">
                <p class="font-bold text-gray-800">@${message.userId.username}</p>
                <p class="text-sm text-gray-600 italic">${formatDateTime(message.createdAt)}</p>
            </div>
        </div>
        <div class="">
            <p class="text-gray-700 text-base">${message.message}</p>
        </div>
    `;

    return messageElement;
}

// Format the timestamp for readability
function formatTime(timestamp) {
    const date = new Date(timestamp);
    return date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }) + 
           ", " + 
           date.toLocaleDateString();
}


// Send a message through WebSocket
function sendMsg(senderId, communityId) {
    const messageInput = document.getElementById("messageInput");
    const message = messageInput.value.trim();
    if (!message)
        return;

    const messagePayload = {
        senderId: senderId,
        communityId: communityId,
        isReply: false,
        message: message,
        reply: ""
    };

    if (socket && socket.readyState === WebSocket.OPEN) {
        socket.send(JSON.stringify(messagePayload));
        messageInput.value = ""; // Clear the input field
    } else {
        console.error("WebSocket connection is not open.");
    }
}

// Attach Enter key event to send messages
document.getElementById("messageInput").addEventListener("keydown", (event) => {
    if (event.key === "Enter") {
        event.preventDefault();
        document.getElementById("sendButton").click();
    }
});


function formatDateTime(dateString) {

    if (!dateString) {
        return;
    }
    // Remove [UTC] if present
    const cleanedDateString = dateString.replace(/\[UTC\]/, "");
    const inputDate = new Date(cleanedDateString);

    if (isNaN(inputDate.getTime())) {
        console.error("Invalid date format");
        return "Invalid Date";
    }

    const now = new Date();
    const diffInSeconds = Math.floor((now - inputDate) / 1000); // Difference in seconds

    // Helper function to add leading zeros
    const pad = (num) => (num < 10 ? `0${num}` : num);

    if (diffInSeconds < 60) {
        // Less than 1 minute
        return `${diffInSeconds} seconds ago`;
    } else if (diffInSeconds < 3600) {
        // Less than 1 hour
        const minutes = Math.floor(diffInSeconds / 60);
        return `${minutes} minutes ago`;
    } else if (diffInSeconds < 43200) {
        // Less than 24 hours, same day
        const hours = inputDate.getHours() % 12 || 12; // Convert to 12-hour format
        const minutes = pad(inputDate.getMinutes());
        const seconds = pad(inputDate.getSeconds());
        const ampm = inputDate.getHours() >= 12 ? "PM" : "AM";
        return `${hours}:${minutes}:${seconds} ${ampm}`;
    } else {
        // More than 24 hours, display full date and time
        const day = pad(inputDate.getDate());
        const monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
        const month = monthNames[inputDate.getMonth()];
        const year = inputDate.getFullYear().toString().slice(-2);
        const hours = pad(inputDate.getHours() % 12 || 12);
        const ampm = inputDate.getHours() >= 12 ? "PM" : "AM";

        const minutes = pad(inputDate.getMinutes());
        const seconds = pad(inputDate.getSeconds());
        return `${hours}:${minutes}:${seconds} ${ampm} ${day} ${month} ${year}`;
    }
}