import mysql.connector
from tkinter import Tk, Label, Entry, Button

conn = mysql.connector.connect(host="localhost", user="root", password="nchhillar", database="coc")
    
# Create a cursor object to execute SQL queries
cursor = conn.cursor()

# Construct the SQL query to insert data into the table
sql = "INSERT INTO troops (name, space, target, movement_speed, attack_speed, raange, level, damage_per_second, damage_per-attack, hitpoints, mini_troops_spawned, training_time) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"

values = ["Archer", 1, "Any", 1, 1, 1, 1, 1, 1, 1, 1, 1]

# Execute the SQL query
cursor.execute(sql, values)

# Commit the changes to the database
conn.commit()

# Close the cursor and database connection
cursor.close()
conn.close()
    