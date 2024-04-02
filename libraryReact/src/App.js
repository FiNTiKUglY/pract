import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';

function App() {

    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true);

        fetch('api/users')
        .then(response => response.json())
        .then(data => {
            setUsers(data);
            setLoading(false);
        })
    }, []);

    if (loading) {
        return <p>Loading...</p>;
    }

    return (
        <div className="App">
            <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <div className="App-intro">
                {users.map(user =>
                    <div key={user.id}>
                        {user.surname}
                    </div>
                )}
            </div>
      </header>
    </div>
  );
}

export default App;
