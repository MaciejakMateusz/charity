document.addEventListener("DOMContentLoaded", function () {

    /**
     *
     * @type {Element}
     * Query selectors used for summary
     */
    const bagsSummary = document.querySelector('#bags-summary');
    const institutionSummary = document.querySelector('#foundation-summary');
    const streetSummary = document.querySelector('#street-summary');
    const citySummary = document.querySelector('#city-summary');
    const zipCodeSummary = document.querySelector('#zipcode-summary');
    const phoneSummary = document.querySelector('#phone-summary');
    const dateSummary = document.querySelector('#date-summary');
    const timeSummary = document.querySelector('#time-summary');
    const commentSummary = document.querySelector('#comment-summary');

    /**
     * Form Select
     */
    class FormSelect {
        constructor($el) {
            this.$el = $el;
            this.options = [...$el.children];
            this.init();
        }

        init() {
            this.createElements();
            this.addEvents();
            this.$el.parentElement.removeChild(this.$el);
        }

        createElements() {
            // Input for value
            this.valueInput = document.createElement("input");
            this.valueInput.type = "text";
            this.valueInput.name = this.$el.name;

            // Dropdown container
            this.dropdown = document.createElement("div");
            this.dropdown.classList.add("dropdown");

            // List container
            this.ul = document.createElement("ul");

            // All list options
            this.options.forEach((el, i) => {
                const li = document.createElement("li");
                li.dataset.value = el.value;
                li.innerText = el.innerText;

                if (i === 0) {
                    // First clickable option
                    this.current = document.createElement("div");
                    this.current.innerText = el.innerText;
                    this.dropdown.appendChild(this.current);
                    this.valueInput.value = el.value;
                    li.classList.add("selected");
                }

                this.ul.appendChild(li);
            });

            this.dropdown.appendChild(this.ul);
            this.dropdown.appendChild(this.valueInput);
            this.$el.parentElement.appendChild(this.dropdown);
        }

        addEvents() {
            this.dropdown.addEventListener("click", e => {
                const target = e.target;
                this.dropdown.classList.toggle("selecting");

                // Save new value only when clicked on li
                if (target.tagName === "LI") {
                    this.valueInput.value = target.dataset.value;
                    this.current.innerText = target.innerText;
                }
            });
        }
    }

    document.querySelectorAll(".form-group--dropdown select").forEach(el => {
        new FormSelect(el);
    });

    /**
     * Hide elements when clicked on document
     */
    document.addEventListener("click", function (e) {
        const target = e.target;
        const tagName = target.tagName;

        if (target.classList.contains("dropdown")) return false;

        if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
            return false;
        }

        if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
            return false;
        }

        document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
            el.classList.remove("selecting");
        });
    });

    /**
     * Switching between form steps
     */
    class FormSteps {
        constructor(form) {
            this.$form = form;
            this.$next = form.querySelectorAll(".next-step");
            this.$prev = form.querySelectorAll(".prev-step");
            this.$step = form.querySelector(".form--steps-counter span");
            this.currentStep = 1;

            this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
            const $stepForms = form.querySelectorAll("form > div");
            this.slides = [...this.$stepInstructions, ...$stepForms];

            this.init();
        }

        /**
         * Init all methods
         */
        init() {
            this.events();
            this.updateForm();
        }

        /**
         * All events that are happening in form
         */
        events() {
            // Next step
            this.$next.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep++;
                    this.updateForm();
                });
            });

            // Previous step
            this.$prev.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep--;
                    this.updateForm();
                });
            });

            // Form submit
            this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
        }

        /**
         * Update form front-end
         * Show next or previous section etc.
         */
        updateForm() {
            this.$step.innerText = this.currentStep;

            // TODO: Validation

            this.slides.forEach(slide => {
                slide.classList.remove("active");

                if (slide.dataset.step == this.currentStep) {
                    slide.classList.add("active");
                }
            });

            this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
            this.$step.parentElement.hidden = this.currentStep >= 5;

            // Summary
            const donationsForm = document.querySelector('#donations-form');
            const formData = new FormData(donationsForm);

            let bagsQuantity = formData.get("quantity");
            let institutionId = formData.get("institution");
            let street = formData.get("street");
            let city = formData.get("city");
            let zipCode = formData.get("zipCode");
            let phone = formData.get("phone");
            let date = formData.get("pickUpDate");
            let time = formData.get("pickUpTime");
            let pickUpComment = formData.get("pickUpComment");

            if(institutionId !== null) {
                fetchInstitutionById(institutionId).then(data => {
                    institutionSummary.innerText = `Dla fundacji ${data.name}`;
                });
            }

            bagsSummary.innerText = `${bagsQuantity} sztuk(i) worków z kategorii: `;
            streetSummary.innerText = street;
            citySummary.innerText = city;
            zipCodeSummary.innerText = zipCode;
            phoneSummary.innerText = phone;
            dateSummary.innerText = date;
            timeSummary.innerText = time;
            commentSummary.innerText = pickUpComment;
        }
    }

    const form = document.querySelector(".form--steps");
    if (form !== null) {
        new FormSteps(form);
    }
});

/**
 * Api host address
 */
const apiHost = 'http://localhost:8080';

function fetchInstitutionById(id) {
    return fetch(`${apiHost}/api/institution/${id}`)
        .then(response => {
            if(response.ok) {
                return response.json();
            } else {
                throw new Error("Communication error: GET /api/institution/{id}");
            }
        }).then(data => {
            return data;
        }).catch(error => {
            console.log(error);
        });
}
function fetchCategoryById(id) {
    return fetch(`${apiHost}/api/category/${id}`)
        .then(response => {
            if(response.ok) {
                return response.json();
            } else {
                throw new Error("Communication error: GET /api/category/{id}");
            }
        }).then(data => {
            return data;
        }).catch(error => {
            console.log(error);
        });
}