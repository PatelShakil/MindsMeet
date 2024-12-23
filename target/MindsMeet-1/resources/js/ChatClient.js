
const APP_URL = "localhost:8080/MindsMeet/";
// WebSocket instance
let socket = null;
// Receiver's user ID
let receiverId = null;
let senderId = $("#senderId").val();
let receiver = null;
// Open chat for a specific user
function openChat(userId, name,prof) {
    // Ensure receiver is updated correctly
    receiver = {
        id: userId,
        name: name,
        profile: prof // Set the profile image URL dynamically here
    };
    
    $("#selectError").hide();
    receiverId = userId; // Set the receiver ID
    $("#receiverName").text(name);
// Remove the selected class from all users and add it to the clicked user
    $(".user-item").removeClass("bg-green-200");
    $(`#user_${userId}`).addClass("bg-green-200");
    $("#messageArea").empty(); // Clear previous messages
    fetchChatHistory(senderId, receiverId); // Load chat history


    if (!socket || socket.readyState !== WebSocket.OPEN) {
        initializeWebSocket(); // Reinitialize WebSocket if needed
    }
}


//                         // Initialize WebSocket connection
function initializeWebSocket() {
    if (!socket) {
        socket = new WebSocket(`ws://${APP_URL}chat/` + senderId);
        socket.onopen = function () {
            console.log("WebSocket connection established.");
        };
        socket.onmessage = function (event) {
            handleIncomingMessage(event.data);
        };
        socket.onerror = function (error) {
            console.error("WebSocket encountered an error:", error);
        };
        socket.onclose = function () {
            console.log("WebSocket connection closed.");
        };
    }
}
//
//        
//               // Fetch chat history for the selected user
function fetchChatHistory(senderId, userId) {
    $.ajax({
        url: `http://${APP_URL}api/v1/chat/history/` + senderId + "/" + userId,
        method: "GET",
        success: function (messages) {


            $.each(messages, function (index, message) {
                const isSender = message.senderId.id == senderId; // Replace dynamically                
                addMessageToUI(message, isSender);
                
            });
        },
        error: function (error) {
            console.error("Error fetching chat history:", error);
        }
    });
}
// Handle the send button click

function sendMsg() {
    const messageText = $("#messageInput").val().trim();
    if (messageText) {
        if (receiverId) {
            const messagePayload = {
                senderId: senderId, // Replace dynamically
                receiverId: receiverId,
                message: messageText,
                createdAt:new Date().toLocaleString()
            };
            // Send message via WebSocket
            socket.send(JSON.stringify(messagePayload));
            addMessageToUI(messagePayload, true); // Add message to UI as sender
            $("#messageInput").val(""); // Clear the input field
        } else {
            alert("Please Select User First!!!");
        }
    }
}
// Dynamically add a message to the chat UI
function addMessageToUI(message, isSender) {
    const messageArea = document.getElementById("messageArea");
    
//    const isSender = message.senderId.id == senderId;

    const messageWrapper = document.createElement("div");
    messageWrapper.classList.add("flex", "mb-4", isSender ? "justify-end" : "justify-start");

    const messageBubble = document.createElement("div");
    messageBubble.classList.add(
            "p-4",
            "rounded-lg",
            "shadow-md",
            isSender ? "bg-green-100" : "bg-blue-100",
            isSender ? "rounded-br-none" : "rounded-bl-none",
            "max-w-xs"
            );

    messageBubble.innerHTML = `
        <p class="text-sm text-gray-700">${message.message}</p>
        <p class="text-xs text-gray-500 text-right mt-1">${formatDateTime(message.createdAt)}</p>
    `;

    if (!isSender) {
        const profileImage = document.createElement("img");
        profileImage.src = `http://localhost:8080/MindsMeet/resources/uploads/images/${receiver.profile}`;
        profileImage.alt = "Profile";
        profileImage.classList.add("h-8", "w-8", "rounded-full", "mr-2");

        messageWrapper.appendChild(profileImage);
    }

    messageWrapper.appendChild(messageBubble);
    messageArea.appendChild(messageWrapper);
    // Auto-scroll to the bottom
    messageArea.scrollTop = messageArea.scrollHeight;
}


// Add incoming WebSocket messages to the UI
function handleIncomingMessage(data) {
    try {
        const message = JSON.parse(data);
        const isSender = message.senderId.id === senderId; // Replace dynamically
        addMessageToUI(message, isSender);
    } catch (error) {
        console.error("Error processing incoming WebSocket message:", error);
    }
}
// Attach Enter key event to send messages
$("#messageInput").keydown((event) => {
    if (event.key === "Enter") {
        event.preventDefault();
        $("#sendButton").click();
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