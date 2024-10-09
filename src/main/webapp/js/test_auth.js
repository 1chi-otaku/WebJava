function TestAuthPage({ contextPath }) {
    const [response, setResponse] = React.useState([]);

    const sendRequest = async (headerValue) => {
        const result = await fetch(`${contextPath}/auth`, {
            method: 'GET',
            headers: {
                'Authorization': headerValue
            }
        });

        const data = await result.json();

        const isSuccess = data.code === 200;
        setResponse(prev => [...prev, { result: data, isSuccess }]);
    };

    const runTests = async () => {
        setResponse([]);

        await sendRequest('Not really Basic ' + btoa(`user:password`));

        await sendRequest("Basic " + btoa("user!password"));

        const invalidBasicAuthHeader ='Basic ' + btoa(`user:password}`) + "hee-ho!";
        await sendRequest(invalidBasicAuthHeader);

        const basicAuthHeader = 'Basic ' + btoa(`user:password`);
        await sendRequest(basicAuthHeader);

    };

    return (
        <div className="container mt-5">
            <h1>Tests</h1>
            <button className="btn btn-primary" onClick={runTests}>
                Start the test!
            </button>
            <div className="mt-4">
                {response.map((resp, index) => (
                    <div key={index} style={{ color: resp.isSuccess ? 'green' : 'red' }}>
                        <pre>{JSON.stringify(resp.result, null, 2)}</pre>
                    </div>
                ))}
            </div>
        </div>
    );
}

const domRoot = document.getElementById("test-app");
const cp = domRoot.getAttribute("data-context-path");
ReactDOM.createRoot(domRoot).render(<TestAuthPage contextPath={cp} />);
