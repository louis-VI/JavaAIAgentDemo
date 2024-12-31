


const messagesDiv = document.getElementById('messages');
const queryInput = document.getElementById('query');
const sendBtn = document.getElementById('send-btn');
const qrCodeContainer = document.getElementById('qrcode-container');
const label = document.getElementById('label');

// 请求部分立即显示
function appendQuery(text, className) {
    const messageWrapper = document.createElement('div');
    messageWrapper.className = `message-wrapper ${className}`;

    const messageDiv = document.createElement('div');
    messageDiv.className = 'message';
    messageDiv.textContent = text;

    messageWrapper.appendChild(messageDiv);
    messagesDiv.appendChild(messageWrapper);
    messagesDiv.scrollTop = messagesDiv.scrollHeight;
}

// 回复部分使用打字机效果
function appendMessage(text, className) {
    const messageWrapper = document.createElement('div');
    messageWrapper.className = `message-wrapper ${className}`;

    const messageDiv = document.createElement('div');
    messageDiv.className = 'message';
    messageWrapper.appendChild(messageDiv);
    messagesDiv.appendChild(messageWrapper);
    typeWriter(messageDiv, text);
}

function appendCodeMessage(code) {
    const messageWrapper = document.createElement('div');
    messageWrapper.className = 'message-wrapper message-bot';

    const messageDiv = document.createElement('div');
    messageDiv.className = 'message message-code';
    messageWrapper.appendChild(messageDiv);
    messagesDiv.appendChild(messageWrapper);
    typeWriter(messageDiv, code);
}

function typeWriter(element, text, index = 0) {
    if (index < text.length) {
        if (text.charAt(index) === '\n') {
            element.appendChild(document.createElement('br'));
        } else {
            const span = document.createElement('span');
            span.textContent = text.charAt(index);
            element.appendChild(span);
        }
        setTimeout(() => typeWriter(element, text, index + 1), 16); // 更改打字速度
    } else {
        messagesDiv.scrollTop = messagesDiv.scrollHeight;
    }
}

function sendPostRequest() {
    const query = queryInput.value;
    if (!query) return;

    // 隐藏二维码
    qrCodeContainer.style.display = 'none';
    label.style.display = 'none';

    appendQuery(query, 'message-user');
    queryInput.value = '';

    // 添加加载动画
    const loadingWrapper = document.createElement('div');
    loadingWrapper.className = 'message-wrapper message-bot';
    const loadingDiv = document.createElement('div');
    loadingDiv.className = 'message';
    const spinner = document.createElement('div');
    spinner.className = 'loading-spinner';
    loadingDiv.appendChild(spinner);
    loadingWrapper.appendChild(loadingDiv);
    messagesDiv.appendChild(loadingWrapper);

    // 滚动到输入框位置
    queryInput.scrollIntoView({ behavior: 'smooth', block: 'end', inline: 'nearest' });

    fetch('/chat', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ query }),
    })
        .then(response => response.text())
        .then(data => {
            console.log('Success:', data);
            try {
                const jsonData = JSON.parse(data);
                if (typeof jsonData === 'object' && jsonData !== null) {
                    appendCodeMessage(JSON.stringify(jsonData, null, 2));
                } else {
                    appendMessage(data, 'message-bot');
                }
            } catch (error) {
                appendMessage(data, 'message-bot');
            } finally {
                // 移除加载动画
                loadingWrapper.remove();
                // 滚动到输入框位置
                queryInput.scrollIntoView({ behavior: 'smooth', block: 'end', inline: 'nearest' });
            }
        })
        .catch((error) => {
            console.error('Error:', error);
            appendMessage('Error: ' + error.message, 'message-bot');
            // 移除加载动画
            loadingWrapper.remove();
            // 滚动到输入框位置
            queryInput.scrollIntoView({ behavior: 'smooth', block: 'end', inline: 'nearest' });
        });
}
sendBtn.addEventListener('click', sendPostRequest);

queryInput.addEventListener('keypress', function(event) {
    if (event.key === 'Enter') {
        if (event.shiftKey) {
            // 按下 Shift + Enter 插入换行符
            event.preventDefault();
            const cursorPos = queryInput.selectionStart;
            const textBeforeCursor = queryInput.value.substring(0, cursorPos);
            const textAfterCursor = queryInput.value.substring(cursorPos);
            queryInput.value = textBeforeCursor + '\n' + textAfterCursor;
            queryInput.selectionStart = queryInput.selectionEnd = cursorPos + 1;

            // 滚动到输入框位置
            queryInput.scrollIntoView({ behavior: 'smooth', block: 'end', inline: 'nearest' });
        } else {
            // 按下 Enter 发送消息
            event.preventDefault();
            sendPostRequest();

            // 滚动到输入框位置
            queryInput.scrollIntoView({ behavior: 'smooth', block: 'end', inline: 'nearest' });
        }
    }
});

// 在点击输入框时隐藏二维码和标签
queryInput.addEventListener('focus', function() {
    qrCodeContainer.style.display = 'none';
    label.style.display = 'none';
});