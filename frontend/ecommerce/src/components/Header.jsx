import { Link } from "react-router-dom";

function Header() {
    return (
        <nav className="navbar navabar-expand-lg navbar-light bg-light p-3">
            <div className="container-fluid">
                <Link className="navbar-brand" to="/">🛍️ E-Commerce</Link>
                <Link className="mx-2" to="/products">Products</Link>
                <Link className="mx-2" to="/cart">Cart</Link>
                <Link className="mx-2" to="/orders">Orders</Link>
            </div>
        </nav>
    );
}

export default Header;