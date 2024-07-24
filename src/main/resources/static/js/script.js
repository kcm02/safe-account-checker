document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('submitButton').addEventListener('click', async function() {
        const bankName = document.getElementById('dropdown').value;
        const accountNumber = document.getElementById('userInput').value;

        try {
            const response = await fetch('/validate', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: new URLSearchParams({
                    bankName: bankName,
                    accountNumber: accountNumber
                })
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            const data = await response.json();
            // 'message' 필드만 추출하여 모달에 표시
            showModal(data.message || '응답이 없습니다.');
        } catch (error) {
            showModal(`오류 발생: ${error.message}`);
        }
    });

    function showModal(message) {
        document.getElementById('modalBody').innerText = message;
        const modal = document.getElementById('responseModal');
        modal.style.display = 'block';

        document.querySelector('.close').onclick = function() {
            modal.style.display = 'none';
        };

        document.getElementById('closeButton').onclick = function() {
            modal.style.display = 'none';
        };

        window.onclick = function(event) {
            if (event.target === modal) {
                modal.style.display = 'none';
            }
        };
    }
});
