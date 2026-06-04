function validateArticleForm() {

    const title = document.getElementById("title").value.trim();
    const content = document.getElementById("content").value.trim();

    if (title === "") {
        alert("Article title cannot be blank.");      

        return false;
    }

    if (content.length < 10) {
        alert("Article content must be at least 10 characters.");  

        return false;
    }

    return true;
}

