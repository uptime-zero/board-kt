async function apiFetch(url, options = {}) {
    const response = await fetch(url, {
        ...options,
        credentials: "include",
        headers: {
            "Content-Type": "application/json",
            ...options.headers,
        },
    });

    const hasBody = response.status !== 204;
    const data = hasBody ? await response.json().catch(() => null) : null;

    if (!response.ok) {
        const message = data?.message || "요청 처리 중 오류가 발생했습니다.";
        throw new Error(message);
    }

    return data;
}

function formatDate(isoString) {
    if (!isoString) return "";
    const date = new Date(isoString);
    return date.toLocaleDateString("ko-KR", {
        year: "numeric",
        month: "2-digit",
        day: "2-digit",
    });
}

function showFormError(elementId, message) {
    const el = document.getElementById(elementId);
    if (!el) return;
    el.textContent = message;
    el.style.display = "block";
}