async function searchBooks() {

    const title = document.getElementById("searchInput").value;

    const response = await fetch(
        `/api/books/search?title=${title}`
    );

    const books = await response.json();

    const results = document.getElementById("book_search_results");

    results.innerHTML = "";

    if (books.length === 0) {
        results.innerHTML =
            "<div class='alert alert-warning'>No books found</div>";
        return;
    }

    books.forEach(book => {

        results.innerHTML += `
            <div class="col-md-4">
                <div class="card p-3 h-100 shadow-sm">
                    <h4>${book.title}</h4>
                    <p><strong>ISBN:</strong> ${book.isbn}</p>
                    <p>
                        <span class="badge ${
                            book.availableCopies > 0
                                ? 'bg-success'
                                : 'bg-danger'
                        }">
                            ${book.availableCopies} copies
                        </span>
                    </p>

                    <button
                        class="btn btn-success mt-auto"
                        onclick="loanBook(${book.id})"
                        ${book.availableCopies === 0
                            ? 'disabled'
                            : ''}>
                        Loan Book
                    </button>
                </div>
            </div>`;
    });
}

async function loanBook(bookId) {

    const userName = prompt("Enter user name:");

    if (!userName) return;

    const expectedReturnDate =
        prompt("Expected return date (YYYY-MM-DD):");

    if (!expectedReturnDate) return;

    const response = await fetch("/api/loans", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            bookId,
            userName,
            expectedReturnDate
        })
    });

    if (response.ok) {

        alert("Loan created successfully!");

        searchBooks();

    } else {

        const error = await response.text();

        alert(error);
    }
}