package GUtils;

import Repository.Kunder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public interface Command {
    void execute(Optional<Kunder> match) throws SQLException, IOException;
}
