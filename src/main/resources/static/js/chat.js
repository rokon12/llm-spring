document.addEventListener('DOMContentLoaded', function() {
    // DOM Elements
    const messageInput = document.getElementById('messageInput');
    const sendButton = document.getElementById('sendButton');
    const chatMessages = document.getElementById('chatMessages');
    const chatButton = document.getElementById('chatButton');
    const chatContainer = document.getElementById('chatContainer');
    const minimizeButton = document.getElementById('minimizeButton');
    const maximizeButton = document.getElementById('maximizeButton');
    const backdrop = document.getElementById('backdrop');

    // Chat Functions
    function toggleChat() {
        chatContainer.classList.toggle('show');
        if (chatContainer.classList.contains('show')) {
            messageInput.focus();
        }
    }

    function toggleMaximize() {
        chatContainer.classList.toggle('maximized');
        backdrop.classList.toggle('show');
        const icon = maximizeButton.querySelector('i');
        if (chatContainer.classList.contains('maximized')) {
            icon.classList.remove('bi-arrows-angle-expand');
            icon.classList.add('bi-arrows-angle-contract');
            maximizeButton.title = "Restore";
        } else {
            icon.classList.remove('bi-arrows-angle-contract');
            icon.classList.add('bi-arrows-angle-expand');
            maximizeButton.title = "Maximize";
        }
    }

    function closeMaximized() {
        if (chatContainer.classList.contains('maximized')) {
            toggleMaximize();
        }
    }

    function handleKeyPress(e) {
        if (e.key === 'Escape') {
            if (chatContainer.classList.contains('maximized')) {
                toggleMaximize();
            } else if (chatContainer.classList.contains('show')) {
                toggleChat();
            }
        }
    }

    async function sendMessage() {
        const message = messageInput.value.trim();
        if (message) {
            addMessage(message, true);
            messageInput.value = '';

            try {
                const response = await fetch(`/assistant?message=${encodeURIComponent(message)}`);
                const reply = await response.text();
                addMessage(reply, false);
            } catch (error) {
                console.error('Error:', error);
                addMessage('Sorry, there was an error processing your message.', false);
            }
        }
    }

    function addMessage(message, isUser) {
        const messageDiv = document.createElement('div');
        messageDiv.className = `message ${isUser ? 'user' : 'assistant'}`;
        
        const messageContent = document.createElement('div');
        messageContent.className = 'message-content';
        messageContent.textContent = message;
        
        messageDiv.appendChild(messageContent);
        chatMessages.appendChild(messageDiv);
        chatMessages.scrollTop = chatMessages.scrollHeight;
    }

    // Event Listeners
    chatButton.addEventListener('click', toggleChat);
    minimizeButton.addEventListener('click', toggleChat);
    maximizeButton.addEventListener('click', toggleMaximize);
    backdrop.addEventListener('click', closeMaximized);
    document.addEventListener('keydown', handleKeyPress);
    sendButton.addEventListener('click', sendMessage);
    messageInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            sendMessage();
        }
    });
});