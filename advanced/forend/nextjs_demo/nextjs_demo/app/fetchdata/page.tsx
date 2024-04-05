async function getData() {
    const res = await fetch("http://localhost/greeting2", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    });

    // The return value is *not* serialized
    // You can return Date, Map, Set, etc.
    if (!res.ok) {
        // This will activate the closest `error.js` Error Boundary
        throw new Error('Failed to fetch data')
    }

    return res.json();
}

export default async function Page() {
    const data = await getData()
    console.log("data:{}",data)
    return (
        <main>
            <h1>Data Fetched</h1>
            <pre>{JSON.stringify(data, null, 2)}</pre>
        </main>
    );
}