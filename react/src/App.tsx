import { useEffect } from 'react';
import reactLogo from './assets/react.svg';
import viteLogo from '/vite.svg';
import './App.css';
import { redirectToLogin, validateToken, logout } from './services/auth';
import { getCookie } from './utils';

function App() {
    const jwt_token = getCookie('jwt_token');

    useEffect(() => {
        validateToken().then((user) => {
            if (!user || !jwt_token) {
                redirectToLogin();
            }
        });
    }, []);

    return (
        <>
            <div>
                <a href="https://vite.dev" target="_blank">
                    <img src={viteLogo} className="logo" alt="Vite logo" />
                </a>
                <a href="https://react.dev" target="_blank">
                    <img src={reactLogo} className="logo react" alt="React logo" />
                </a>
            </div>
            <h1>Vite + React</h1>
            <button onClick={logout}>Logout</button> {/* Add a logout button */}
            <p>Welcome to the app!</p>
        </>
    );
}

export default App;