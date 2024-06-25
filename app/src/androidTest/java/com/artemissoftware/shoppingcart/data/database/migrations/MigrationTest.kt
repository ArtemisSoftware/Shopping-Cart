package com.artemissoftware.shoppingcart.data.database.migrations

import androidx.room.Room
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import assertk.assertions.isTrue
import com.artemissoftware.shoppingcart.data.database.ShoppingCartDatabase
import com.artemissoftware.shoppingcart.data.database.migrations.ManualMigration.migration3To4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val DB_NAME = "test"

@RunWith(AndroidJUnit4::class)
class MigrationTest {

    @get:Rule
    val helper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        ShoppingCartDatabase::class.java,
        AutomaticMigration.ALL_MIGRATIONS,
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    fun `migration 1 To 2 contains correct data`() {
        var db = helper.createDatabase(DB_NAME, 1).apply {
            execSQL("INSERT INTO products VALUES(1, 'table', 'my table', 2, 3.0, 'image.jpg')")
            close()
        }

        db = helper.runMigrationsAndValidate(DB_NAME, 2, true)

        db.query("SELECT * FROM products").apply {
            assertThat(moveToFirst())
                .isTrue()

            assertThat(arrayOf("id", "name", "description", "amount", "price", "imageUrl", "comments"))
                .isEqualTo(columnNames)

            assertThat(getString(getColumnIndex("comments")))
                .isEqualTo("N/A")
        }
    }

    @Test
    fun `migration 3 To 4 contains correct data`() {
        var db = helper.createDatabase(DB_NAME, 3)

        db = helper.runMigrationsAndValidate(DB_NAME, 4, true, migration3To4)

        db.query("SELECT * FROM sellers").apply {

            assertThat(arrayOf("uuid", "name", "imageUrl"))
                .isEqualTo(columnNames)
        }
    }

    @Test
    fun `migration 4 To 5 contains correct data`() {
        var db = helper.createDatabase(DB_NAME, 4).apply {
            execSQL("INSERT INTO products VALUES(1, 'table', 'my table', 2, 3.0, 'image.jpg', 'my comments')")
            close()
        }

        db = helper.runMigrationsAndValidate(DB_NAME, 5, true)

        db.query("SELECT * FROM products").apply {
            assertThat(moveToFirst())
                .isTrue()

            assertThat(arrayOf("id", "name", "description", "amount", "price", "imageUrl", "comments", "sellerId"))
                .isEqualTo(columnNames)

            assertThat(getString(getColumnIndex("sellerId")))
                .isNull()
        }
    }

    @Test
    fun `test all migrations`() {
        helper.createDatabase(DB_NAME, 1).apply { close() }

        Room.databaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            ShoppingCartDatabase::class.java,
            DB_NAME
        ).addMigrations(*ManualMigration.ALL_MIGRATIONS).build().apply {
            openHelper.writableDatabase.close()
        }
    }
}