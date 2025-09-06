-- Insert sample recycler centers
INSERT INTO recycler_centers (name, description, location, city, contact_info) VALUES
('Green Earth Recycling', 'Safe disposal of electronics and recovery of valuable materials.', '123 Green Street', 'New Delhi', '+91 9876543210'),
('EcoSmart E-Waste', 'Certified recycling for computers, phones, and household electronics.', '456 Eco Road', 'Mumbai', '+91 9876543211'),
('SafeTech Recycling', 'Advanced recycling technology and zero landfill policy.', '789 Tech Avenue', 'Bengaluru', '+91 9876543212'),
('Clean Future Recyclers', 'Expert handling of large-scale corporate e-waste.', '321 Clean Lane', 'Hyderabad', '+91 9876543213'),
('GreenCycle Waste Management', 'Refurbishing and reusing electronic components.', '654 Cycle Road', 'Chennai', '+91 9876543214'),
('Urban Recyclers', 'Community e-waste drives and safe disposal services.', '987 Urban Street', 'Pune', '+91 9876543215'),
('EcoRenew', 'Specializing in mobile device recycling and refurbishing.', '147 Renew Road', 'Ahmedabad', '+91 9876543216'),
('RecycleHub', 'E-waste collection points in multiple cities.', '258 Hub Lane', 'Jaipur', '+91 9876543217'),
('TechWaste Solutions', 'Corporate and residential e-waste recycling services.', '369 Solution Avenue', 'Kolkata', '+91 9876543218');

-- Insert admin user
INSERT INTO users (name, email, password, address, phone, city, role) VALUES
('Admin User', 'admin@ewasteportal.com', 'admin123', 'Admin Address', '+91 9999999999', 'New Delhi', 'ADMIN');