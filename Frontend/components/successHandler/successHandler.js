function handleSuccess(success) {
        const message = success + " war erfolgreich!";
        const successDisplay = new SuccessDisplay(message);
        successDisplay.display();
}

function SuccessDisplay(successMessage) {
    this.toastTemplate = `
        <div class="toast" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-header">
                <div class="rounded me-2" style="background-color: green; width: 15px; height: 15px;"></div>
                <strong class="ml-5">Success</strong>
            </div>
            <div class="toast-body">
                <p>$MESSAGE</p>
            </div>
        </div>
    `;

    this.successMessage = successMessage;

    this.display = function () {
        const toast = $(this.toastTemplate.replace("$MESSAGE", this.successMessage));
        $(".toast-container").append(toast);
        bootstrap.Toast.getOrCreateInstance(toast).show();
    }
}