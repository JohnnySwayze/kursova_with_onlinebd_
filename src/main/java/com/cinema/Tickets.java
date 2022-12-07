package com.cinema;
public class Tickets {
    private int id_tickets;
    private String name_of_movie;
    private String hall_name;
    private String data;
    private String oclock;
    private int num_of_place;
    private int row_of_place;
    private String condition_of_place;


    public Tickets(int id_tickets, String name_of_movie, String hall_name, String data, String oclock,int row_of_place,
                   int num_of_place, String condition_of_place) {
        this.id_tickets = id_tickets;
        this.name_of_movie = name_of_movie;
        this.hall_name = hall_name;
        this.data = data;
        this.oclock = oclock;
        this.row_of_place=row_of_place;
        this.num_of_place = num_of_place;
        this.condition_of_place = condition_of_place;
    }

    public int getRow_of_place() {
        return row_of_place;
    }

    public void setRow_of_place(int row_of_place) {
        this.row_of_place = row_of_place;
    }

    public int getId_tickets ()
        {
            return this.id_tickets;
        }
        public void setId_tickets ( int id_tickets)
        {
            this.id_tickets = id_tickets;
        }
        public String getName_of_movie ()
        {
            return this.name_of_movie;
        }
        public void setName_of_movie (String name_of_movie)
        {
            this.name_of_movie = name_of_movie;
        }
        public String getHall_name ()
        {
            return this.hall_name;
        }
        public void setHall_name (String hall_name)
        {
            this.hall_name = hall_name;
        }
        public String getData ()
        {
            return this.data;
        }
        public void setData (String data)
        {
            this.data = data;
        }

        public String getOclock ()
        {
            return this.oclock;
        }

        public void setOclock (String oclock)
        {
            this.oclock = oclock;
        }

        public int getNum_of_place ()
        {
            return this.num_of_place;
        }

        public void setNum_of_place ( int num_of_place)
        {
            this.num_of_place = num_of_place;
        }

        public String getCondition_of_place ()
        {
            return this.condition_of_place;
        }

        public void setCondition_of_place (String condition_of_place)
        {
            this.condition_of_place = condition_of_place;
        }


}
