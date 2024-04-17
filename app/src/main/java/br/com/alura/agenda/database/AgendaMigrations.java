package br.com.alura.agenda.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

class AgendaMigrations {
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN sobrenome TEXT");
        }
    };

    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // SQLite não permite remover uma coluna (4 passos necessários para fazer isso):
            // Criar nova tabela com as informações desejadas
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `telefone` TEXT, `email` TEXT)");
            // Copiar dados da tabela antiga para a nova
            database.execSQL("INSERT INTO Aluno_novo (id, nome, telefone, email) SELECT id, nome, telefone, email FROM Aluno");
            // Remover tabela antiga
            database.execSQL("DROP TABLE Aluno");
            // Renomear a tablea nova com o nome da tabela antiga
            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };

    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE Aluno ADD COLUMN momentoDeCadastro INTEGER");
        }
    };

    static final Migration[] TODAS_MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4};
}
