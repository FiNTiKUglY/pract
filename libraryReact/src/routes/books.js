import React, { useState, useEffect } from 'react';
import { getBooks, deleteBook } from "../api/bookService"
import { Link } from "react-router-dom";


export default function Books() {
    const [books, setBooks] = useState([]);
    const [reload, setReload] = useState(false);

    useEffect(() => {
        loadBooks()
    }, [reload]);

    return (
        <section >
            <div className='table-title'>
                <h2>Книги</h2>
                
                <table className="table table-striped table-hover">   
                    <thead>
                        <tr>
                            <th>
                                Название
                            </th>
                            <th>
                                Автор
                            </th>
                            <th>
                                Описание
                            </th>
                            <th>
                                Цена
                            </th>
                            <th>
                                Ссылка загрузки
                            </th>
                            <th>
                                Ссылка загрузки
                            </th>
                            <th>
                                Жанры
                            </th>
                            <th>
                                <Link to="add/" className="btn btn-success">Добавить</Link>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        {books.map(book =>
                            <tr key={book.id}>
                                <td>
                                    {book.title}
                                </td>
                                <td>
                                    {book.author.surname} {book.author.name}
                                </td>
                                <td>
                                    {book.shortDescription}
                                </td>
                                <td>
                                    {book.cost}
                                </td>
                                <td>
                                    {book.downloadLink}
                                </td>
                                <td>
                                    {book.imageLink}
                                </td>
                                <td>
                                    {book.genres.map(genre=>
                                        genre.name 
                                    )}
                                </td>
                                <td>
                                    <Link to="update/" className="btn btn-info">Изменить</Link>
                                    |
                                    <button 
                                        onClick={() => removeBook(book.id)} 
                                        value={book.id} 
                                        className="btn btn-danger">
                                            Удалить
                                    </button>
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </section>
    );

    async function loadBooks() {
        const books = await getBooks()
        setBooks(books)
    }

    async function removeBook(id) {
        await deleteBook(id)
        setReload(!reload)
    }
}