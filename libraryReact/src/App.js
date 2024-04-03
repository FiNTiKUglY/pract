import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';

function App() {

    const [books, setBooks] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true);

        fetch('api/books')
        .then(response => response.json())
        .then(data => {
            setBooks(data);
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
                {books.map(book =>
                    <div key={book.id}>
                        {book.title}
                    </div>
                )}
            </div>
      </header>
    </div>
  );
}

export default App;
